import {Page,chromium,Browser} from '@playwright/test'
import { Before,After ,Status} from '@cucumber/cucumber'
import {pagefixer} from "./pagefixer"
import {BrowserContext} from '@playwright/test'
import { BeforeAll,AfterAll} from '@cucumber/cucumber'
//import { pagefixer } from 'G:/Suresh/Playwright_Cuumber_POM/src/tests/step/pagefixer'
import { invokeBrowser } from "../../helper/browsers/browserManager"
import { getEnv } from "../../helper/env/env"

let browser: Browser;
let context : BrowserContext

BeforeAll( async function(){
    getEnv();
    browser = await invokeBrowser();
})
AfterAll(async function(){
    await browser.close();
})
    Before (async function(){
        context = await browser.newContext();
       const page = await context.newPage();
       pagefixer.page = page;
    });  
    After(async function({pickle,result}){
       if(result?.status == Status.PASSED){
            const img = await pagefixer.page.screenshot({path: `./test-result/screenshot/${pickle.name}.png`, type: "png" });
            this.attach(img,"image/png");
    }
        await pagefixer.page.close();
        await context.close();       
    });