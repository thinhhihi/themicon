package com.dev.hieu.da1app;

public interface Constants {
    /*Bang User*/
    // Ten Bangcpu
    String TABLE_CPU = "CPU";

    // Ten Cot
    String COLUMN_IDCPU = "IDCPU";
    String COLUMN_TITLECPU = "TitleCPU";
    String COLUMN_SHORTDESCCPU = "ShortDescCPU";
    String COLUMN_PRICECPU = "PriceCPU";
    String COLUMN_RATINGCPU = "RatingCPU";
    String COLUMN_IMGCPU = "ImgCPU";


    // Cau lenh tao bang
    String CREATE_TABLE_CPU = "CREATE TABLE " + TABLE_CPU + "(" +

            COLUMN_IDCPU + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLECPU + " NVARCHAR," +

            COLUMN_SHORTDESCCPU + " NVARCHAR," +
            COLUMN_PRICECPU + " NVARCHAR," +
            COLUMN_IMGCPU + " NVARCHAR," +

            COLUMN_RATINGCPU + " NVARCHAR" +

            ")";

    String TABLE_GPU = "GPU";

    // Ten Cot
    String COLUMN_IDGPU = "IDGPU";
    String COLUMN_TITLEGPU = "TitleGPU";
    String COLUMN_SHORTDESGPU = "ShortDescGPU";
    String COLUMN_PRICEGPU = "PriceGPU";
    String COLUMN_RATINGGPU = "RatingGPU";
    String COLUMN_IMGGPU = "ImgGPU";

    // Cau lenh tao bang
    String CREATE_TABLE_GPU = "CREATE TABLE " + TABLE_GPU + "(" +

            COLUMN_IDGPU + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLEGPU + " NVARCHAR," +

            COLUMN_SHORTDESGPU + " NVARCHAR," +
            COLUMN_PRICEGPU + " NVARCHAR," +
            COLUMN_IMGGPU + " NVARCHAR," +

            COLUMN_RATINGGPU + " NVARCHAR" +

            ")";

    String TABLE_MAIN = "MAIN";

    // Ten Cot
    String COLUMN_IDMAIN = "IDMain";
    String COLUMN_TITLEMAIN = "TitleMain";
    String COLUMN_SHORTDESGMAIN = "ShortDescMain";
    String COLUMN_PRICEMAIN = "PriceMain";
    String COLUMN_RATINGMAIN = "RatingMain";
    String COLUMN_IMGMAIN = "ImgMain";


    // Cau lenh tao bang
    String CREATE_TABLE_MAIN = "CREATE TABLE " + TABLE_MAIN + "(" +

            COLUMN_IDMAIN + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLEMAIN + " NVARCHAR," +

            COLUMN_SHORTDESGMAIN + " NVARCHAR," +
            COLUMN_PRICEMAIN + " NVARCHAR," +
            COLUMN_IMGMAIN + " NVARCHAR," +

            COLUMN_RATINGMAIN + " NVARCHAR" +

            ")";

    String TABLE_HDD = "HDD";

    // Ten Cot
    String COLUMN_IDHDD = "IDHDD";
    String COLUMN_TITLEHDD = "TitleHDD";
    String COLUMN_SHORTDESGHDD = "ShortDescHDD";
    String COLUMN_PRICEHDD = "PriceHDD";
    String COLUMN_RATINGHDD = "RatingHDD";
    String COLUMN_IMGHDD = "ImgHDD";


    // Cau lenh tao bang
    String CREATE_TABLE_HDD = "CREATE TABLE " + TABLE_HDD + "(" +

            COLUMN_IDHDD + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLEHDD + " NVARCHAR," +

            COLUMN_SHORTDESGHDD + " NVARCHAR," +
            COLUMN_PRICEHDD + " NVARCHAR," +
            COLUMN_IMGHDD + " NVARCHAR," +

            COLUMN_RATINGHDD + " NVARCHAR" +

            ")";

    String TABLE_RAM = "RAM";

    // Ten Cot
    String COLUMN_IDRAM = "IDRAM";
    String COLUMN_TITLERAM = "TitleRAM";
    String COLUMN_SHORTDESGRAM = "ShortDescRAM";
    String COLUMN_PRICERAM = "PriceRAM";
    String COLUMN_RATINGRAM = "RatingRAM";
    String COLUMN_IMGRAM = "ImgRAM";

    // Cau lenh tao bang
    String CREATE_TABLE_RAM = "CREATE TABLE " + TABLE_RAM + "(" +

            COLUMN_IDRAM + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLERAM + " NVARCHAR," +

            COLUMN_SHORTDESGRAM + " NVARCHAR," +

            COLUMN_PRICERAM + " NVARCHAR," +
            COLUMN_IMGRAM + " NVARCHAR," +

            COLUMN_RATINGRAM + " NVARCHAR" +

            ")";


    String TABLE_PSU = "PSU";

    // Ten Cot
    String COLUMN_IDPSU = "IDPSU";
    String COLUMN_TITLEPSU = "TitlePSU";
    String COLUMN_SHORTDESPSU = "ShortDescPSU";
    String COLUMN_PRICEPSU = "PricePSU";
    String COLUMN_RATINGPSU = "RatingPSU";
    String COLUMN_IMGPSU = "ImgPSU";


    // Cau lenh tao bang
    String CREATE_TABLE_PSU = "CREATE TABLE " + TABLE_PSU + "(" +

            COLUMN_IDPSU + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLEPSU + " NVARCHAR," +

            COLUMN_SHORTDESPSU + " NVARCHAR," +
            COLUMN_PRICEPSU + " NVARCHAR," +
            COLUMN_IMGPSU + " NVARCHAR," +

            COLUMN_RATINGPSU + " NVARCHAR" +

            ")";

    String TABLE_SSD = "SSD";

    // Ten Cot
    String COLUMN_IDSSD = "IDSSD";
    String COLUMN_TITLESSD = "TitleSSD";
    String COLUMN_SHORTDESSSD = "ShortDescSSD";
    String COLUMN_PRICESSD = "PriceSSD";
    String COLUMN_RATINGSSD = "RatingSSD";
    String COLUMN_IMGSSD = "ImgSSD";


    // Cau lenh tao bang
    String CREATE_TABLE_SSD = "CREATE TABLE " + TABLE_SSD + "(" +

            COLUMN_IDSSD + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLESSD + " NVARCHAR," +

            COLUMN_SHORTDESSSD + " NVARCHAR," +
            COLUMN_PRICESSD + " NVARCHAR," +
            COLUMN_IMGSSD + " NVARCHAR," +

            COLUMN_RATINGSSD + " NVARCHAR" +

            ")";

    String TABLE_PC = "PC";

    // Ten Cot
    String COLUMN_IDPC = "IDPC";
    String COLUMN_TITLEPC = "TitlePC";
    String COLUMN_SHORTDESPC = "ShortDescPC";
    String COLUMN_PRICEPC = "PricePC";
    String COLUMN_RATINGPC = "RatingPC";
    String COLUMN_IMGPC = "ImgPC";


    // Cau lenh tao bang
    String CREATE_TABLE_PC = "CREATE TABLE " + TABLE_PC + "(" +

            COLUMN_IDPC + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLEPC + " NVARCHAR," +

            COLUMN_SHORTDESPC + " NVARCHAR," +
            COLUMN_PRICEPC + " NVARCHAR," +
            COLUMN_IMGPC + " NVARCHAR," +

            COLUMN_RATINGPC + " NVARCHAR" +

            ")";



    String TABLE_CART = "Cart";
    String COLUMN_IDCART = "IdCart";
    String COLUMN_TITLECART = "TitleCart";
    String COLUMN_SHORTDESCCART = "ShortdescCart";
    String COLUMN_PRICECART = "PriceCart";
    String COLUMN_RATINGCART = "RatingCart";
    String COLUMN_IMGCART = "ImgCart";

    String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + "(" +

            COLUMN_IDCART + " NVARCHAR PRIMARY KEY," +

            COLUMN_TITLECART + " NVARCHAR," +

            COLUMN_SHORTDESCCART + " NVARCHAR," +

            COLUMN_RATINGCART + " NVARCHAR," +
            COLUMN_IMGCART + " NVARCHAR," +


            COLUMN_PRICECART + " NVARCHAR" +

            ")";




}
