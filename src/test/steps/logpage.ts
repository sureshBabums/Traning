import {Given, setDefaultTimeout} from "@cucumber/cucumber"
import {shinohomepage} from "../../pages/shinohomepage"
import {pagefixer} from "../hooks/pagefixer"

setDefaultTimeout(60 * 1000 * 2)
//let Shinohomepage = new shinohomepage(pagefixer.page);
Given('To Test the application',async function () { 
    let Shinohomepage = new shinohomepage(pagefixer.page);
    await Shinohomepage.goToURL(process.env.BASEURL);
    await Shinohomepage.ParkingLot();
    await Shinohomepage.StartingDates();
    await Shinohomepage.StartingTimes();
    await Shinohomepage.LeavingDates();
    await Shinohomepage.LeavingTimes();
    await Shinohomepage.Submits(); 

 });