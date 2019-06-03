package com.example.firstspringboot.common.utils;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelBatchExportService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * @author: sunwenwu
 * @Date: 2019/4/18 16：11
 * @Description:
 */
public class MyExcelExportUtil extends ExcelBatchExportService{

    private static Logger LOGGER = LoggerFactory.getLogger(MyExcelExportUtil.class);

    /**
     * @param entity
     *            表格标题属性
     * @param pojoClass
     *            Excel对象Class
     * @param dataSet
     *            Excel对象数据List
     */
    public static Workbook exportBigExcel(ExportParams entity, Class<?> pojoClass,
                                          Collection<?> dataSet) {
        ExcelBatchExportService batchService = getExcelBatchExportService(entity, pojoClass);
        return batchService.appendData(dataSet);
    }

    public static void createBigExcelSheet(Workbook workbook,ExportParams entity, Class<?> pojoClass) {

        try {
            ExcelBatchExportService batchService = getExcelBatchExportService(entity, pojoClass);

            List<ExcelExportEntity> entityList = batchService.createExcelExportEntityList(entity, pojoClass);

            batchService.createSheet(workbook,entity,entityList);
            Field sheetFile = ExcelBatchExportService.class.getDeclaredField("sheet");
            sheetFile.setAccessible(true);
            Sheet sheet = (Sheet)sheetFile.get(batchService);
            Method insertDataToSheet = ExcelBatchExportService.class.getDeclaredMethod("insertDataToSheet",Workbook.class,ExportParams.class,List.class,Collection.class,Sheet.class);
            insertDataToSheet.setAccessible(true);
            insertDataToSheet.invoke(batchService,workbook,entity,entityList,null,sheet);
        } catch (Exception e) {
            LOGGER.error("创建sheet页失败",e);
        }
    }


    public static void main(String[] args) {



    }
}
