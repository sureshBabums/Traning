module.exports= {
        default: {
            tags: process.env.npm_config_TAGS || "",
            formatOptions: {
                snippetInterface: "async-await"
            },
            paths: [
                "src/test/features/*.feature"
              
            ],
            dryRun: false,
            require: [
                "src/test/steps/*.ts",
                "src/test/hooks/hook.ts"
            ],
            format:[
                "progress-bar",
                "html:test-results/cucumber-report.html",
                "json:test-results/cucumber-report.json",
                "rerun:@rerun.txt"
            ],
            requireModule: [
                "ts-node/register"
            ],
            parallel: 2
    
            
        },
        rerun: {
            formatOptions: {
                snippetInterface: "async-await"
            },
            
            dryRun: false,
            require: [
                "src/test/steps/*.ts",
               "src/test/hooks/hook.ts"
            ],
            format:[
                "progress-bar",
                "html:test-results/cucumber-report.html",
                "json:test-results/cucumber-report.json",
                "rerun:@rerun.txt"
            ],
            requireModule: [
                "ts-node/register"
            ],
            parallel: 2
    
            
        }

}