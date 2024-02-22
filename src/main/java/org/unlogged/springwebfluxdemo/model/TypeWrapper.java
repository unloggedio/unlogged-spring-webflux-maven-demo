package org.unlogged.springwebfluxdemo.model;

public class TypeWrapper {
    private int intValue;
    private long longValue;
    private char charValue;
    private double doubleValue;
    private float floatValue;
    private String stringValue;
    private short shortValue;
    private byte byteValue;
    private boolean boolValue;

    private Integer intWrapper;
    private Long longWrapper;
    private Character charWrapper;
    private Double doubleWrapper;
    private Float floatWrapper;
    private Short shortWrapper;
    private Byte ByteWrapper;
    private Boolean booleanWrapper;

    private Object object;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public void setCharValue(char charValue) {
        this.charValue = charValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public void setShortValue(short shortValue) {
        this.shortValue = shortValue;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public void setByteValue(byte byteValue) {
        this.byteValue = byteValue;
    }

    public boolean isBoolValue() {
        return boolValue;
    }

    public void setBoolValue(boolean boolValue) {
        this.boolValue = boolValue;
    }

    public Integer getIntWrapper() {
        return intWrapper;
    }

    public void setIntWrapper(Integer intWrapper) {
        this.intWrapper = intWrapper;
    }

    public Long getLongWrapper() {
        return longWrapper;
    }

    public void setLongWrapper(Long longWrapper) {
        this.longWrapper = longWrapper;
    }

    public Character getCharWrapper() {
        return charWrapper;
    }

    public void setCharWrapper(Character charWrapper) {
        this.charWrapper = charWrapper;
    }

    public Double getDoubleWrapper() {
        return doubleWrapper;
    }

    public void setDoubleWrapper(Double doubleWrapper) {
        this.doubleWrapper = doubleWrapper;
    }

    public Float getFloatWrapper() {
        return floatWrapper;
    }

    public void setFloatWrapper(Float floatWrapper) {
        this.floatWrapper = floatWrapper;
    }

    public Short getShortWrapper() {
        return shortWrapper;
    }

    public void setShortWrapper(Short shortWrapper) {
        this.shortWrapper = shortWrapper;
    }

    public Byte getByteWrapper() {
        return ByteWrapper;
    }

    public void setByteWrapper(Byte byteWrapper) {
        ByteWrapper = byteWrapper;
    }

    public Boolean getBooleanWrapper() {
        return booleanWrapper;
    }

    public void setBooleanWrapper(Boolean booleanWrapper) {
        this.booleanWrapper = booleanWrapper;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "TypeWrapper{" +
                "intValue=" + intValue +
                ", longValue=" + longValue +
                ", charValue=" + charValue +
                ", doubleValue=" + doubleValue +
                ", floatValue=" + floatValue +
                ", stringValue='" + stringValue + '\'' +
                ", shortValue=" + shortValue +
                ", byteValue=" + byteValue +
                ", boolValue=" + boolValue +
                ", intWrapper=" + intWrapper +
                ", longWrapper=" + longWrapper +
                ", charWrapper=" + charWrapper +
                ", doubleWrapper=" + doubleWrapper +
                ", floatWrapper=" + floatWrapper +
                ", shortWrapper=" + shortWrapper +
                ", ByteWrapper=" + ByteWrapper +
                ", booleanWrapper=" + booleanWrapper +
                ", object=" + object +
                '}';
    }
}
