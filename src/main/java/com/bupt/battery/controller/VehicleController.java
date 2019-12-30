package com.bupt.battery.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.battery.AO.ChartAO;
import com.bupt.battery.AO.FileAO;
import com.bupt.battery.AO.TableAO;
import com.bupt.battery.Enum.AreaType;
import com.bupt.battery.config.WebSocket;
import com.bupt.battery.entity.DataManagementDO;
import com.bupt.battery.entity.GranularityDO;
import com.bupt.battery.entity.TaskDO;
import com.bupt.battery.entity.VehicleDO;
import com.bupt.battery.form.DealForm;
import com.bupt.battery.form.GranularityForm;
import com.bupt.battery.form.VehicleForm;
import com.bupt.battery.form.VehicleSaveForm;
import com.bupt.battery.repository.IDataManagementDORepository;
import com.bupt.battery.service.IDataManagementDOService;
import com.bupt.battery.service.IGranularityDOService;
import com.bupt.battery.service.IVehicleDOService;
import com.bupt.battery.task.CleanTask;
import com.bupt.battery.task.CleanThread;
import com.bupt.battery.task.TaskThreadPoolExecutor;
import com.bupt.battery.util.DateUtil;
import com.bupt.battery.util.EnumUtil;
import com.bupt.battery.util.ExportExcel;
import com.bupt.battery.util.FileUtil;
import com.bupt.battery.util.Student;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import schemasMicrosoftComVml.STExt.Enum;

@RestController
@RequestMapping(path = "/api/vehicle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
public class VehicleController {
    @Autowired
    private IVehicleDOService vehicleDOService;
    @Autowired
    private IGranularityDOService granularityDOService;
    @Autowired
    private IDataManagementDOService dataManagementDOService;
    @Autowired
    private TaskThreadPoolExecutor pool;

    @Value("${vehicleFile.url}")
    private String filePath;

    @RequestMapping(path = "/list")
    public List<ChartAO> queryVehicleList(@RequestBody GranularityForm form) {
        System.out.println(form + "-----------");
        List<GranularityDO> granularityDOList = granularityDOService.findGranularityList(form);
        return getChartAO(granularityDOList);
    }

    @RequestMapping(path = "/page")
    public Page<VehicleDO> getVehiclePage(@RequestBody GranularityForm form) {
        System.out.println(form);
        return vehicleDOService.findVehicleDOPage(form);
    }
    @RequestMapping(path = "/getVehicle")
    public VehicleDO getVehicle(@RequestBody VehicleSaveForm form)
    {
        VehicleDO vehicleDO=vehicleDOService.getOne(form.getId());
        vehicleDO.setGroupMode(vehicleDO.getGroupMode().substring(vehicleDO.getGroupMode().indexOf("_")+1));
        vehicleDO.setVehicleVersion(vehicleDO.getVehicleVersion().substring(vehicleDO.getVehicleVersion().indexOf("_")+1));
        vehicleDO.setBoxPosition(vehicleDO.getBoxPosition().substring(vehicleDO.getBoxPosition().indexOf("_")+1));

        return vehicleDO;
    }

    @RequestMapping(path = "/upload")
    public FileAO upload(HttpServletRequest request,@RequestParam(value = "file1",required = false)MultipartFile file1,@RequestParam(value = "file2",required = false)MultipartFile file2,@RequestParam(value = "file3",required = false)MultipartFile file3) throws Exception{
        String groupMode="";
        if(file1!=null)
        {
            groupMode=uploadFile(file1);
        }
        String version="";
        if(file2!=null)
        {
            version=uploadFile(file2);
        }
        String bosPosition="";
        if(file3!=null)
        {
            bosPosition=uploadFile(file3);
        }
        VehicleDO vehicleDO=new VehicleDO();
        String amountString=request.getParameter("amount").trim().equals("undefined")?"0":request.getParameter("amount").trim();
        vehicleDO.setAmount(Integer.valueOf(amountString));
        vehicleDO.setVehicleId(Integer.valueOf(request.getParameter("vehicleId").trim()));
        vehicleDO.setCarmaker(request.getParameter("carmaker"));
        vehicleDO.setOrderNumber(request.getParameter("orderNumber"));
        vehicleDO.setDrivingArea(request.getParameter("drivingArea"));
        vehicleDO.setMode(request.getParameter("mode"));
        vehicleDO.setVehicleUsage(request.getParameter("vehicleUsage"));
        vehicleDO.setBatteryType(request.getParameter("batteryType"));
        vehicleDO.setVin(request.getParameter("vin"));
        vehicleDO.setPlateNumber(request.getParameter("plateNumber"));
        String lengthString=request.getParameter("carLength").trim().equals("undefined")?"0":request.getParameter("carLength").trim();
        vehicleDO.setCarLength(Float.valueOf(lengthString));
//        System.out.println(request.getParameter("onlineDate"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(request.getParameter("onlineDate"));
        vehicleDO.setOnlineDate(date);
        vehicleDO.setThermalManagement(request.getParameter("thermalManagement"));
        vehicleDO.setVehicleSystem(request.getParameter("vehicleSystem"));
        vehicleDO.setBusCompany(request.getParameter("busCompany"));
        vehicleDO.setMileage(Integer.valueOf(request.getParameter("mileage").trim()));
        vehicleDO.setBusRoutes(request.getParameter("busRoutes"));
        vehicleDO.setGroupMode(groupMode);
        vehicleDO.setVehicleVersion(version);
        vehicleDO.setBoxPosition(bosPosition);
        vehicleDO.setAnalysisResult("无");
        System.out.println(vehicleDO);
        vehicleDOService.save(vehicleDO);
        System.out.println(groupMode+"-"+version+"-"+bosPosition);
        FileAO fileAO=new FileAO();
        fileAO.setUrl(groupMode);
        return fileAO;
    }
    @GetMapping(path = "/getFile")
    public void getFile(@RequestParam String type,@RequestParam Long id,HttpServletResponse resp) throws IOException{
        VehicleDO vehicleDO=vehicleDOService.getOne(id);
        String path = null;
        String name=null;
        if (type.equals("成组方式"))
        {
            name=vehicleDO.getGroupMode();
        }
        else if(type.equals("版本号")){
            name=vehicleDO.getVehicleVersion();
         }
        else {
            name=vehicleDO.getBoxPosition();
        }
        path=filePath+name;
        //String realPath = "D:" + File.separator + "apache-tomcat-8.5.15" + File.separator + "files";
        File file = new File(path);
        System.out.println(path);
        if(!file.exists()){
            throw new IOException("文件不存在");
        }
        //        resp.reset();
        resp.setContentType("application/octet-stream");
        //        resp.setContentType("application/x-png");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.addHeader("Content-Disposition", "attachment;filename=" + name);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @RequestMapping(path = "/delete")
    public void delete(@RequestBody GranularityForm form) {
        vehicleDOService.delete(form.getId());
    }

    @RequestMapping(path = "/getData")
    public TableAO getData(@RequestBody GranularityForm form) {
        Integer sum=dataManagementDOService.findAll().stream().map(dataManagementDO -> {
            return dataManagementDO.getAmount();
        }).reduce((integer, integer2) -> {
            return integer+integer2;
        }).get();
        List<GranularityDO> granularityDOList = granularityDOService.findGranularityList(form);
        List<ChartAO> chartAOList = getChartAO(granularityDOList);
        System.out.println(form.toString());
        System.out.println(chartAOList.toString());
        List<String> timeList = granularityDOList.stream().map(granularityDO -> granularityDO.getTime()).collect(Collectors.toList());
        timeList = new ArrayList<>(new HashSet<>(timeList));
        List<String> typeList = granularityDOList.stream().map(granularityDO -> granularityDO.getType()).collect(Collectors.toList());
        typeList = new ArrayList<>(new HashSet<>(typeList));
        List<String> areaList = granularityDOList.stream().map(granularityDO -> granularityDO.getArea()).collect(Collectors.toList());
        areaList = new ArrayList<>(new HashSet<>(areaList));
        Collections.sort(timeList);
        Collections.sort(areaList);
        Collections.sort(typeList);
        List<List<Object>> listList = new ArrayList<>();
        if (StringUtils.isNotBlank(form.getType())) {

            for (int i = 0; i < timeList.size(); i++) {
                List<Object> list1 = new ArrayList<>();
                for (int j = 0; j < areaList.size(); j++) {
                    if (j == 0) {
                        list1.add(timeList.get(i));
                    }
                    Integer amount = getAmountBy(chartAOList, timeList.get(i), form.getType(), areaList.get(j));
                    amount=amount != null ? amount : 0;
                    list1.add(amount+"/"+String.format("%.2f", 1.0*amount/sum*100)+"%");
                }
                listList.add(list1);
            }
        } else if (StringUtils.isNotBlank(form.getArea())) {

            for (int i = 0; i < timeList.size(); i++) {
//                System.out.println("222");
                List<Object> list1 = new ArrayList<>();
//                System.out.println(timeList.size());
//                System.out.println(typeList.size());
                for (int j = 0; j < typeList.size(); j++) {
//                    System.out.println("22");
                    if (j == 0) {
                        list1.add(timeList.get(i));
                    }
                    Integer amount = getAmountBy(chartAOList, timeList.get(i), typeList.get(j), form.getArea());
                    amount=amount != null ? amount : 0;
                    list1.add(amount+"/"+String.format("%.2f", 1.0*amount/sum*100)+"%");
                }
                listList.add(list1);
            }
        } else if (StringUtils.isNotBlank(form.getTime())) {
            for (int i = 0; i < typeList.size(); i++) {
                List<Object> list1 = new ArrayList<>();
                for (int j = 0; j < areaList.size(); j++) {
                    if (j == 0) {
                        list1.add(typeList.get(i));
                    }
                    Integer amount = getAmountBy(chartAOList, form.getTime(), typeList.get(i), areaList.get(j));
                    amount=amount != null ? amount : 0;
                    list1.add(amount+"/"+String.format("%.2f", 1.0*amount/sum*100)+"%");
                }
                listList.add(list1);
            }
        }
        TableAO tableAO = new TableAO();
        tableAO.setTypeList(typeList);
        tableAO.setTimeList(timeList);
        tableAO.setAreaList(areaList);
        tableAO.setAmountList(listList);
        System.out.println(tableAO);
        return tableAO;
    }

    @RequestMapping(path = "/chart")
    public List<ChartAO> getChart() {
        List<GranularityDO> granularityDOList = granularityDOService.findAll();
        return getChartAO(granularityDOList);
    }

    @RequestMapping(path = "/deal")
    public void deal(@RequestBody DealForm form) {
        String data = getData(form.getDealList());
        JSONObject object = new JSONObject();
        object.put("time", form.getTime());
        object.put("area", form.getArea());
        object.put("type", form.getType());
        //object.put("data", data);
        CleanThread cleanThread = new CleanThread(object.toJSONString(), form.shopId);
        pool.execute(cleanThread);
    }

    @GetMapping(path = "/download")
    public void download(@RequestParam String area, @RequestParam String type, @RequestParam String time, HttpServletResponse response) {
        System.out.println(area);
        String year = time.split("-")[0];
        String month = time.split("-")[1];
        Date startDate = DateUtil.getFirstDayOfMonth(Integer.valueOf(year), Integer.valueOf(month));
        Date endDate = DateUtil.getLastDayOfMonth(Integer.valueOf(year), Integer.valueOf(month));
        List<VehicleDO> vehicleDOList = vehicleDOService.findVehicleDOS(area, type, startDate, endDate);
        String[] columnNames = {"车辆编号", "订单号", "整车厂", "运行地区", "运行模式", "用法", "电池类型", "成组方式", "车架号", "车牌号", "版本号", "箱体位置", "车长", "上线时间", "数量", "热管理", "整车系统", "公交公司", "运行里程", "公交路线", "运行分析结果"};
        System.out.println(vehicleDOList);
        //        List<Student> list = new ArrayList<>();
        //        for (int i = 0; i < 10; i++) {
        //            list.add(new Student(111,"张三asdf","男"));
        //            list.add(new Student(111,"李四asd","男"));
        //            list.add(new Student(111,"王五","女"));
        //        }
        //        String[] columnNames = { "ID", "姓名", " 性别"};
        String fileName = "车辆数据";
        ExportExcel<VehicleDO> util = new ExportExcel<VehicleDO>();
        util.exportExcel(fileName, fileName, columnNames, vehicleDOList, response, ExportExcel.EXCEl_FILE_2007);
    }

    private List<ChartAO> getChartAO(List<GranularityDO> granularityDOList) {
        return granularityDOList.stream().map(granularityDO -> {
            ChartAO chartAO = new ChartAO();
            chartAO.setArea(granularityDO.getArea());
            chartAO.setTime(granularityDO.getTime());
            //            chartAO.setAreaName(EnumUtil.getAreaType(granularityDO.getArea()).getName());
            //            chartAO.setTypeName(EnumUtil.getBatteryType(granularityDO.getType()).getName());
            chartAO.setType(granularityDO.getType());
            DataManagementDO dataManagementDO = dataManagementDOService.findDataManagementDOByGranId(granularityDO.getId());
            chartAO.setAmount(dataManagementDO.getAmount());
            chartAO.setIsComplete(dataManagementDO.getIsComplete());
            chartAO.setDeal(dataManagementDO.getDeal());

            return chartAO;
        }).collect(Collectors.toList());
    }

    private Integer getAmountBy(List<ChartAO> granularityDOList, String time, String type, String area) {
        System.out.println(granularityDOList.size());
        for (int i = 0; i < granularityDOList.size(); i++) {
//            System.out.println(i);
            if (granularityDOList.get(i).getType().equals(type) && granularityDOList.get(i).getTime().equals(time) && granularityDOList.get(i).getArea().equals(area)) {

                return granularityDOList.get(i).getAmount();
            }
        }
        return null;
    }

    private String getData(List<String> dealList) {
        String data = "";
        if (dealList.contains("SOC预处理")) {
            data += "1";
        } else {
            data += "0";
        }
        if (dealList.contains("温度预处理")) {
            data += "1";
        } else {
            data += "0";
        }
        if (dealList.contains("电压预处理")) {
            data += "1";
        } else {
            data += "0";
        }
        if (dealList.contains("电流预处理")) {
            data += "1";
        } else {
            data += "0";
        }
        if (dealList.contains("去重预处理")) {
            data += "1";
        } else {
            data += "0";
        }
        data += "000";
        System.out.println("data:-----" + data);
        return data;
    }


    private String uploadFile(MultipartFile file)
    {
        String contentType = file.getContentType(); // 图片文件类型
        String fileName =	file.getOriginalFilename();	 // 图片名字
        System.out.println(contentType);
        System.out.println(fileName);
        UUID uuid=UUID.randomUUID();
//        String newfileName = uuid.toString()+fileName.substring(fileName.indexOf('.')); //文件重命名
        //文件存放路径
        String newfileName = uuid.toString()+"_"+fileName;
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, newfileName);//文件处理
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(filePath);
        // 返回图片的存放路径
        return newfileName;
    }

//    public static void main(String[] args) {
//        String s="a_b_c";
//        System.out.println(s.substring(s.indexOf("_")+1));
//    }

}
