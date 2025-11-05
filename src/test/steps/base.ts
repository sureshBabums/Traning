import {test as base} from "@playwright/test";

import {shinohomepage} from "../../pages/shinohomepage"


//import {pagefixer} from "../hooks/pagefixer"



export type TestOptions = {

    shinohomepage : shinohomepage;
    
}

    // homePage: async ({ page }, use) => {
// const testpage = base.extend<TestOptions>(
export const test = base.extend<TestOptions>({


    shinohomepage: async ({page},use)=>{
        await use(new shinohomepage(page))
    },

 

});

export {expect} from "@playwright/test";
//export const test = testpage;
