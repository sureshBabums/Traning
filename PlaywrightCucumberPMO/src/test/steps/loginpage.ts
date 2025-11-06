import {Given,Then, setDefaultTimeout} from "@cucumber/cucumber"
import {shinohomepage} from "../../pages/shinohomepage"
import {pagefixer} from "../hooks/pagefixer"

setDefaultTimeout(60 * 2000 * 2)
//let Shinohomepage = new shinohomepage(pagefixer.page);
Given('User navigates to the application',async function () {
   let Shinohomepage = new shinohomepage(pagefixer.page);
    await Shinohomepage.goToURL(process.env.BASEURL);
    await Shinohomepage.ParkingLot();
    await Shinohomepage.StartingDates();
    await Shinohomepage.StartingTimes();
    await Shinohomepage.LeavingDates();
    await Shinohomepage.LeavingTimes();
    await Shinohomepage.Submits(); 

 });
 Then('user model', async function () {
    let Shinohomepage = new shinohomepage(pagefixer.page);
    await Shinohomepage.goToURL(process.env.BASEURL);
    await Shinohomepage.ParkingLot();
    await Shinohomepage.StartingDates();
    await Shinohomepage.StartingTimes();
    await Shinohomepage.LeavingDates();
    await Shinohomepage.LeavingTimes();
    await Shinohomepage.Submits(); 
 });
 
 Then('Page object Model using Fixer',async ()=>{
    let Shinohomepage = new shinohomepage(pagefixer.page);
    await Shinohomepage.goToURL(process.env.BASEURL);
    await Shinohomepage.ParkingLot();
    await Shinohomepage.StartingDates();
    await Shinohomepage.StartingTimes();
    await Shinohomepage.LeavingDates();
    await Shinohomepage.LeavingTimes();
    await Shinohomepage.Submits(); 
 })
