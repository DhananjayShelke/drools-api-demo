package com.example.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.descr.PackageDescr;
import org.drools.mvel.DrlDumper;

public class DrlFileCreator {

	public static void main(String[] args) {

		PackageDescr pkg = DescrFactory.newPackage()
				.name("org.drools.example").newRule().name("Price Discount")
				.attribute("ruleflow-grou", "discount")
				.lhs().and().pattern("Product").id("$product", false).constraint("name=='Shoes'")
				.constraint("price>1000")
				
				.end()
				.end()
				.end()
				.rhs("$product.setDiscount(100);").end().getDescr();
		DrlDumper dumper = new DrlDumper();
		String drl = dumper.dump(pkg);
		System.out.print(drl);
		try {
			// create new file
			File file = new File("src/main/resources/discount.drl");
			file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(drl);
			// close connection
			bw.close();
			System.out.println("File Created Successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	
	}

}
