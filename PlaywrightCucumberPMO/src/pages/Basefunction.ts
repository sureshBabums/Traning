function highlight(arg0: any) {
    try{
        arg0.evaluate((element) => {
            element.style.backgroundColor = 'yellow'; // Set background color
            element.style.border = '2px solid red'; // Add a border to highlight
        }); 
    }catch(error) {
        console.log(`Failed : ${error.message}`);
        throw new Error(error.message);
      }

   
  }
  export async function fillInputAction(arg0: any, value:string) {
    try {
     highlight(arg0);
    await arg0.fill(value);
    }catch(error) {
      console.log(`Failed : ${error.message}`);
      throw new Error(error.message);
    }
  }
  
  export async function DropDownAction(arg0: any, values: string) {
    try {
      highlight(arg0);
      await arg0.selectOption(values)
  
    } catch (error) {
      console.log(`Failed : ${error.message}`);
      throw new Error(error.message);
    }
  
  }
  
  
  export async function ClickAction(arg0: any) {
    try {
     highlight(arg0);
      await arg0.click();
  
    } catch (error) {
      console.log(`Failed : ${error.message}`);
      throw new Error(error.message);
    }
  }
  
  export async function CheckCheckboxAction(arg0: any) {
    try{
     highlight(arg0);
    await arg0.check();
    
  } catch (error) {
    console.log(`Failed : ${error.message}`);
      throw new Error(error.message);
  }
  }
  export async function UnCheckCheckboxAction(arg0: any) {
    try{
      highlight(arg0);
    await arg0.uncheck();
    
  } catch (error) {
    console.log(`Failed : ${error.message}`);
      throw new Error(error.message);
  }
  }


  export async function Mouseover(arg0: any) {
    try{
      highlight(arg0);
    await arg0.hover();
    
  } catch (error) {
    console.log(`Failed : ${error.message}`);
      throw new Error(error.message);
  }
  }

  export async function DragandDropAction(arg0: any ,arg1 : any) {
    try{
      highlight(arg0);
      highlight(arg1);
      await arg0.dragTo(arg1)
    
  } catch (error) {
    console.log(`Failed : ${error.message}`);
      throw new Error(error.message);
  }
  }

