package jeecg.kxcomm.service.impl.systemmanager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.SystemException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jeecg.kxcomm.entity.contactm.TbOrderDetailEntity;
import jeecg.kxcomm.entity.systemmanager.TbDataRecordEntityEntity;
import jeecg.kxcomm.entity.systemmanager.TbDataSourceEntityEntity;
import jeecg.kxcomm.entity.systemmanager.TbProductTypeEntity;
import jeecg.kxcomm.service.systemmanager.TbDataSourceEntityServiceI;
import jeecg.kxcomm.util.CommonUtil;
import jeecg.kxcomm.vo.hrm.CheckingInstanceVo;
import jeecg.kxcomm.vo.systemmanager.DataBean;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.excel.ExcelUtil;


@Service("tbDataSourceEntityService")
@Transactional
public class TbDataSourceEntityServiceImpl extends CommonServiceImpl implements TbDataSourceEntityServiceI {

	public List<DataBean> listDetailDataRecord(String id) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select t.source_id,t.productType_id,ty.productTypeName,ty.category_id ");
		hql.append(" from tb_data_record t,tb_product_type ty ");
		hql.append(" where t.source_id='"+id+"' ");
		hql.append(" and t.productType_id=ty.productType_id ");
		hql.append(" group by ty.productType_id ");
		
		List lists = this.findListbySql(hql.toString());
		//List lists = this.findByPage(hql.toString(), id);
		List<DataBean> dataBeans = new ArrayList<DataBean>();
		Object[] obj = new Object[lists.size()];
		for (int i = 0; i < lists.size(); i++) {
			obj = (Object[]) lists.get(i);
			DataBean bean = new DataBean();
			bean.setId(obj[0].toString());
			bean.setTypeId(obj[1].toString());
			bean.setName(obj[2].toString());
			bean.setParam(obj[3].toString());
			bean.setUrl("tbDataRecordEntityController.do?datagrid&dataSourceId="+bean.getId()+"&productTypeId="+bean.getTypeId());
			dataBeans.add(bean);
		}
		return dataBeans;
		//return null;
	}

	@Override
	public void delMain(TbDataSourceEntityEntity tbDataSource) {
		//===================================================================================
				//获取参数
				Object id0 = tbDataSource.getId();
				//===================================================================================
				//删除-产品明细
			    String hql0 = "from TbDataRecordEntityEntity where 1 = 1 AND tbDataSource = ? ";
			    List<TbDataRecordEntityEntity> tbDataRecordList = this.findHql(hql0,tbDataSource);
				this.deleteAllEntitie(tbDataRecordList);
				//删除主表信息
				this.delete(tbDataSource);
		
	}

	@Override
	public boolean uploadDataSource(XSSFWorkbook workbook,TbDataSourceEntityEntity dataSource) throws SystemException {
		

		try {
			
			HashMap mapType = new HashMap();
			List<TbProductTypeEntity> tbProductType = this.getList(TbProductTypeEntity.class);
			for (TbProductTypeEntity tmp : tbProductType) {
				mapType.put(tmp.getProducttypename(), tmp);
			}
			XSSFSheet sheet = workbook.getSheetAt(0); // 单元表
			XSSFRow row = null; // 行
			String cell = null; // 列
			
			List<TbDataRecordEntityEntity> recordlist = new ArrayList<TbDataRecordEntityEntity>();
			for (int i = sheet.getFirstRowNum(); i < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				// 判断是否分类，分类则跳出
				if (row.getCell(1) == null
						|| "".equals(row.getCell(1).toString().trim())) {
					cell = row.getCell(0).toString().trim();
					if ("".equals(cell.trim()))
						continue;
				}
				TbDataRecordEntityEntity dataRecord = new TbDataRecordEntityEntity();
				if(null!=row.getCell(0)){
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setProductorderno(CommonUtil.quChuHuanHang(row.getCell(0).getStringCellValue())); // 产品订货号
				}
				if(null!=row.getCell(1)){
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					String productTypeName = row.getCell(1).getStringCellValue().trim();
					TbProductTypeEntity productType = (TbProductTypeEntity) mapType.get(productTypeName);
					if(productType==null)
						continue;
					dataRecord.setTbProductType(productType);
				}
				dataRecord.setTbDataSource(dataSource);
				if(null!=row.getCell(2)){
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setProductdesc(row.getCell(2).getStringCellValue());// 产品描述
				}
				if(null!=row.getCell(3)){
					row.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
					dataRecord.setQuantity((int)row.getCell(3).getNumericCellValue());// 数量
				}
				////////////////////////////////////////////////////////////////////////////
				if(null!=row.getCell(4)){
					row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setUnitprice(row.getCell(4).getStringCellValue());// 目录单价
				}
				if(null!=row.getCell(6)){
					row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setDiscountrate(row.getCell(6).getStringCellValue());// 折扣率
				}
				if(null!=row.getCell(8)){
					row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setOtherrates(row.getCell(8).getStringCellValue());// 运保及其他费率
				}
				if(null!=row.getCell(10)){
					row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setInstallservicecharge(row.getCell(10).getStringCellValue());// 安装服务费
				}
				// 保修费用等于数量的值
				if(null!=row.getCell(11)){
					row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setFirstyear(row.getCell(11).getStringCellValue());// 第一年保修费用
				}
				if(null!=row.getCell(12)){
					row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setSecondyear(row.getCell(12).getStringCellValue());// 第二年保修费用
				}
				if(null!=row.getCell(13)){
					row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setThirdyear(row.getCell(13).getStringCellValue());// 第三年保修费用
				}
				if(null!=row.getCell(16)){
					row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
					dataRecord.setRemark(row.getCell(16).getStringCellValue()); // 备注
				}
				recordlist.add(dataRecord);				
				
			}
			for(TbDataRecordEntityEntity tbDataRecord :recordlist){
				this.save(tbDataRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("请检查excel的数据类型是否符合格式,[String,String,String,Numeric,Numeric,Numeric,Numeric,Numeric,Numeric,Numeric,String]");
		}
		return 	true;
	}
	
}