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
    
    // Words to analyze shipping days
    public static final String[] SHIPPING_DAYS = {"", "within 1 business day", "within 2 business days", "within 3 business days", "within 5 business days", "within 10 business days"};
}
