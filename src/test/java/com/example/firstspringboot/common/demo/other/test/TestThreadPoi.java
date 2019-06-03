package com.example.firstspringboot.common.demo.other.test;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.example.firstspringboot.common.bean.OrderInfo;
import com.example.firstspringboot.common.bean.User;
import com.example.firstspringboot.common.utils.MyExcelExportUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: sunwenwu
 * @Date: 2019/4/26 14：13
 * @Description:
 */
public class TestThreadPoi {
  public static void main(String[] args) throws Exception{
      List<User> sysSaleDataList = new ArrayList<>();

      for (int i=0;i<20000;i++) {
          User ssd = new User(i+":userName",i+":pwd");
          sysSaleDataList.add(ssd);
      }

      Workbook sheets = MyExcelExportUtil.exportBigExcel(new ExportParams(null, "销售数据1"), User.class, sysSaleDataList);
      Workbook sheets2 = MyExcelExportUtil.exportBigExcel(new ExportParams(null, "销售数据2"), User.class, sysSaleDataList);

      downloadExcel(sheets,"销售数据1.xlsx");
      downloadExcel(sheets2,"销售数据2.xlsx");

  }

    private static void downloadExcel(Workbook workbook,String fileName) throws Exception{
        OutputStream out = null;
        try {
            String path = "/opt/excelFile/".concat(DateFormatUtils.format(new Date(), "yyyy/MM/dd/"));
            File file = new File(path);
            if(!file.exists()) {
                file.mkdirs();
            }

            String filePath = path.concat(fileName);

            out = new FileOutputStream(filePath);
            workbook.write(out);
        } finally {
            /*if (out != null) {
                out.close();
            }*/
      System.out.println(fileName+"结束");
        }
    }
}
