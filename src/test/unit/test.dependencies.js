// Libraries
EnvJasmine.loadGlobal(EnvJasmine.libDir + "jquery-1.8.2-min.js");
EnvJasmine.loadGlobal(EnvJasmine.libDir + "angular-1.1.1.js");
EnvJasmine.loadGlobal(EnvJasmine.libDir + "angular-resource-1.1.1.js");
EnvJasmine.loadGlobal(EnvJasmine.libDir + "angular-sanitize-1.1.1.js");

// Testing libraries
EnvJasmine.loadGlobal(EnvJasmine.testDir + "../lib/require/require.js");
EnvJasmine.loadGlobal(EnvJasmine.testDir + "require.conf.js");
EnvJasmine.loadGlobal(EnvJasmine.testDir + "../lib/angular/angular-mocks-1.1.1.js");

// Application
EnvJasmine.loadGlobal(EnvJasmine.rootDir + "app.js");
EnvJasmine.loadGlobal(EnvJasmine.rootDir + "GameController.js");
EnvJasmine.loadGlobal(EnvJasmine.rootDir + "GameServices.js");