/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.constants;

/**
 *
 * @author Nero
 */
public class Constants {
    // URL
    public static final String HARRY_POTTER_SHOP_URL = "https://www.harrypottershop.com";
    public static final String HARRY_POTTER_PLATFORM_934_URL = "https://www.harrypotterplatform934.com";
    public static final String COLOR_PAGE_URL = "https://simple.wikipedia.org/wiki/List_of_colors";
    
    // Directories
    public static final String HARRY_POTTER_SHOP_XSL_PATH = "/WEB-INF/classes/truongvl/xsl/harrypottershop/product.xsl";
    public static final String HARRY_POTTER_SHOP_XSD_PATH = "/WEB-INF/classes/truongvl/xsd/harrypottershop/product.xsd";
    public static final String HARRY_POTTER_SHOP_DTD_PATH = "/WEB-INF/classes/truongvl/dtd/harrypottershop/product.dtd";
    
    public static final String HARRY_POTTER_PLATFORM_XSL_PATH = "/WEB-INF/classes/truongvl/xsl/harrypotterplatform/product.xsl";
    public static final String HARRY_POTTER_PLATFORM_XSD_PATH = "/WEB-INF/classes/truongvl/xsd/harrypotterplatform/product.xsd";
    
    public static final String CHARACTER_XSL_PATH = "/WEB-INF/classes/truongvl/xsl/harrypottershop/character.xsl";
    public static final String CHARACTER_XSD_PATH = "/WEB-INF/classes/truongvl/xsd/harrypottershop/character.xsd";
    
    public static final String HOUSE_XSL_PATH = "/WEB-INF/classes/truongvl/xsl/harrypottershop/house.xsl";
    public static final String HOUSE_XSD_PATH = "/WEB-INF/classes/truongvl/xsd/harrypottershop/house.xsd";
    
    public static final String COLOR_XSL_PATH = "/WEB-INF/classes/truongvl/xsl/color/color.xsl";
    public static final String COLOR_XSD_PATH = "/WEB-INF/classes/truongvl/xsd/color/color.xsd";
    
    // Number
    public static final int HOUSE_GRYFFINDOR = 1;
    public static final int HOUSE_SLYTHERIN = 2;
    public static final int HOUSE_RAVENCLAW = 3;
    public static final int HOUSE_HUFFLEPUFF = 4;
    
    public static final int QUESTION_TYPE_FAVORITE = 1;
    public static final int QUESTION_TYPE_CHECKBOX_LIST = 2;
    public static final int QUESTION_TYPE_RADIO = 3;
    public static final int QUESTION_TYPE_RANGE = 4;
    
    public static final int TARGETED_COLUMN_NAME = 1;
    public static final int TARGETED_COLUMN_CHARACTER = 2;
    public static final int TARGETED_COLUMN_DESCRIPTION = 3;
    public static final int TARGETED_COLUMN_PRICE = 4;
    public static final int TARGETED_COLUMN_NOTE = 5;
    public static final int TARGETED_COLUMN_COLOR = 6;
    public static final int TARGETED_COLUMN_HOUSE = 7;
    public static final int TARGETED_COLUMN_CATEGORY = 8;
    
    // Ignore words
    public static final String[] IGNORE_PHRASES = {"Do you like ", "Do you love ", "How do you feel about "};
}
