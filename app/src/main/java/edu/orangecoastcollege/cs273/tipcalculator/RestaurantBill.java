package edu.orangecoastcollege.cs273.tipcalculator;

/**
 * Calculate a mount of Restaurant bill
 * Created by nhoang53 on 9/8/2016.
 */
public class RestaurantBill {
    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public RestaurantBill()
    {
        mAmount = 0.0;
        mTipPercent = 0.0;
        mTotalAmount = 0.0;
        mTotalAmount = 0.0;
    }

    public RestaurantBill(double mAmount, double mTipPercent) {
        this.mAmount = mAmount;
        this.mTipPercent = mTipPercent;
        recalculateAmount();
    }

    public double getmAmount() {
        return mAmount;
    }

    public double getmTipPercent() {
        return mTipPercent;
    }

    public double getMtipAmount() {
        return mTipAmount;
    }

    public double getmTotalAmount() {
        return mTotalAmount;
    }

    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
        recalculateAmount();
    }

    public void setmTipPercent(double mTipPercent) {
        this.mTipPercent = mTipPercent;
        recalculateAmount();
    }

    public void recalculateAmount()
    {
        mTipAmount = mAmount * mTipPercent;
        mTotalAmount = mTipAmount + mAmount;
    }
}
