import {  Page,Locator } from "@playwright/test";
import { DropDownAction ,fillInputAction,ClickAction } from "./Basefunction"
//import { DropDownAction ,fillInputAction,ClickAction } from "../test/Basefunction";

export class shinohomepage {
    readonly page: Page;
     readonly ParkingLots: any
     readonly StartingDate : Locator
     readonly StartingTime : Locator
     readonly LeavingDate : Locator
     readonly LeavingTime : Locator
     readonly Submit : Locator
     readonly LeavingTimeAMPM :Locator
     readonly StartingTimeAMPM :Locator

    constructor(page: Page) {
        this.page = page;
        this.ParkingLots = page.locator('select[name="ParkingLot"]');   
        this.StartingDate = page.locator("#StartingDate");  
        this.StartingTime = page.locator("#StartingTime");
        this.StartingTimeAMPM = page.locator('input[name="StartingTimeAMPM"]')
        //await ClickAction(StartingTimeAMPM)
        this.LeavingDate = page.locator("#LeavingDate");  
        this.LeavingTime = page.locator("#LeavingTime");     
        this.LeavingTimeAMPM = page.locator('input[name="LeavingTimeAMPM"]')
        //await ClickAction(LeavingTimeAMPM)
        this.Submit = page.locator('input[name="Submit"]')     
    }
async goToURL(arg0:string){
   await this.page.goto(arg0)
}
async ParkingLot(){
    await DropDownAction(this.ParkingLots, "Long-Term Garage Parking")
}
async StartingDates(){
    await fillInputAction(this.StartingDate,"09/12/2025")
}
async StartingTimes(){
    await fillInputAction(this.StartingTime,"12:01")
}
async LeavingDates(){
    await fillInputAction(this.LeavingDate,"10/12/2025")
}
async LeavingTimes(){
    await fillInputAction(this.LeavingTime,"12:02")
}
async Submits(){
    await ClickAction(this.Submit);
}
}