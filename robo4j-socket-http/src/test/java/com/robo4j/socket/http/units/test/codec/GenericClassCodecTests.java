package com.robo4j.socket.http.units.test.codec;

import com.robo4j.socket.http.dto.ClassGetSetDTO;
import com.robo4j.socket.http.dto.FieldValueDTO;
import com.robo4j.socket.http.util.JsonUtil;
import com.robo4j.socket.http.util.ReflectUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Marcus Hirt (@hirt)
 * @author Miroslav Wengner (@miragemiko)
 */
public class GenericClassCodecTests {

	@Test
	public void simpleTest() {
		NSBTypesTestMessage ob1 = new NSBTypesTestMessage(22, "some message", false);
		NSBWithListStringTypesMessage ob2 = new NSBWithListStringTypesMessage();
		ob2.setActive(false);
		ob2.setListText(Arrays.asList("text1", "text2"));
		ob2.setMessage("no message");
		ob2.setNumber(42);

		Map<String, FieldValueDTO> fieldsNSBTypes = ReflectUtils.getFieldsTypeValueMap(NSBTypesTestMessage.class, ob1);
		Map<String, FieldValueDTO> fieldsNSBWithList = ReflectUtils
				.getFieldsTypeValueMap(NSBWithListStringTypesMessage.class, ob2);
		System.out.println("fieldsNSBTypes: " + fieldsNSBTypes);
		System.out.println("fieldsNSBWithList: " + fieldsNSBWithList);
	}

	@Test
	public void genericClassFieldWithNullExtractionTest(){
		int numberValue = 22;
		boolean isActive = true;
		String desiredJson = "{\"number\":"+ numberValue +",\"active\":"+ isActive +"}";

		NSBTypesTestMessage obj1 = new NSBTypesTestMessage(numberValue, null, isActive);
		Map<String, ClassGetSetDTO> fieldsNSBTypes = ReflectUtils.getFieldsTypeMap(NSBTypesTestMessage.class);

		String json = ReflectUtils.createJsonByFieldClassGetter(fieldsNSBTypes, obj1);
		Assert.assertTrue(desiredJson.equals(json));
		System.out.println("JSON: " + json);
	}

	@Test
	public void genericClassFieldExtractionTest() {
	    int numberValue = 22;
	    boolean isActive = true;
	    String message = "some messge";
	    String desiredJson = "{\"number\":"+ numberValue +",\"message\":\""+ message +"\",\"active\":"+ isActive +"}";
		NSBTypesTestMessage obj1 = new NSBTypesTestMessage(numberValue, message, isActive);
		Map<String, ClassGetSetDTO> fieldsNSBTypes = ReflectUtils.getFieldsTypeMap(NSBTypesTestMessage.class);

		String json = ReflectUtils.createJsonByFieldClassGetter(fieldsNSBTypes, obj1);
        Assert.assertTrue(desiredJson.equals(json));
		System.out.println("JSON: " + json);
	}

	@Test
	public void genericClassFiledFromObjectToJsonToObjectTest() {
		NSBTypesTestMessage obj1 = new NSBTypesTestMessage(22, "some messge", true);
		Map<String, ClassGetSetDTO> fieldsNSBTypes = ReflectUtils.getFieldsTypeMap(NSBTypesTestMessage.class);

		String json = ReflectUtils.createJsonByFieldClassGetter(fieldsNSBTypes, obj1);

		NSBTypesTestMessage createdObj = ReflectUtils.createInstanceSetterByFieldMap(NSBTypesTestMessage.class, fieldsNSBTypes,
				JsonUtil.getMapByJson(json));
        Assert.assertTrue(obj1.equals(createdObj));
		System.out.println("Result: " + createdObj);
	}

	@Test
	public void genericClassFiledFromObjectToJsonToObjectWithNulTest() {
		NSBTypesTestMessage obj1 = new NSBTypesTestMessage(22, null, true);
		Map<String, ClassGetSetDTO> fieldsNSBTypes = ReflectUtils.getFieldsTypeMap(NSBTypesTestMessage.class);

		String json = ReflectUtils.createJsonByFieldClassGetter(fieldsNSBTypes, obj1);

		NSBTypesTestMessage createdObj = ReflectUtils.createInstanceSetterByFieldMap(NSBTypesTestMessage.class, fieldsNSBTypes,
				JsonUtil.getMapByJson(json));
		Assert.assertTrue(obj1.equals(createdObj));
		System.out.println("json: " + json);
		System.out.println("Result: " + createdObj);
	}

//	public static Constructor<?> findConstructorByClassAndParameters(Class<?> clazz, List<Class<?>> constructorTypes) {
//		Constructor[] constructors = clazz.getConstructors();
//		//@formatter:off
//        Optional<Constructor> optionalConstructor =  Stream.of(constructors)
//                .filter(c -> c.getParameterTypes().length == constructorTypes.size())
//                .filter(c -> constructorTypes.containsAll(Arrays.asList(c.getParameterTypes())))
//                .findFirst();
//	     if(optionalConstructor.isPresent()){
//	         return optionalConstructor.get();
//	     } else {
//	         throw new RoboReflectException("not valid constructor");
//	     }
//        //@formatter:on
//	}
}
