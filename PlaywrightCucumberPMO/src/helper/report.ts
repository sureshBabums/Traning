const report = require("multiple-cucumber-html-reporter");

const environment = process.env.ENVIRONMENT || 'development';
//const browserName = process.env.BROWSER || 'chrome';
const browserName = process.env.npm_config_BROWSER || process.env.BROWSER || 'Chrome';
const browserVersion = process.env.BROWSER_VERSION || '112';
const projectName = process.env.PROJECT_NAME || 'Shino Application';
const releaseVersion = process.env.RELEASE_VERSION || '1.2.3'; 

report.generate({
    jsonDir: "test-results",
    reportPath: "test-results/reports/",
    reportName: `Playwright Automation Report - ${environment.toUpperCase()}`,
    pageTitle: "Shino App test report",
    displayDuration: false,
    metadata: {
        browser: {
            name: browserName,
            version: browserVersion,
        },
        device: `${process.env.USERNAME || 'User'} - ${process.platform}`,
        platform: {
            name: process.platform === 'win32' ? 'Windows' : 
                  process.platform === 'darwin' ? 'macOS' : 'Linux',
            version: process.env.OS_VERSION || '10',
        },
    },
    customData: {
        title: "Test Info",
        data: [
            { label: "Project", value: projectName },
            { label: "Environment", value: environment.toUpperCase() },
            { label: "Release", value: releaseVersion },
            { label: "Cycle", value: process.env.TEST_CYCLE || "Smoke-1" },
            { label: "Execution Date", value: new Date().toLocaleString() },
            {label: "browserName", value : browserName}
        ],
    },
});