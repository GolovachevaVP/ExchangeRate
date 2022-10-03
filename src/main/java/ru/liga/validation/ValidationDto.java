package ru.liga.validation;

public class ValidationDto {
    private static final int POSITION_FOR_CURRENCY = 0;
    private static final int POSITION_FOR_DATA = 1;

    public static String getDataForCurrency(String input) {
        String[] currencyType = input.split("\\s+");
        return currencyType[POSITION_FOR_CURRENCY];
    }

    public static String getCurrencyForData(String input) {
        String[] dataType = input.split("\\s+");
        if(dataType.length!=1){
            return dataType[POSITION_FOR_DATA];
        }
       else throw new RuntimeException("Неверный запрос");
    }
}
