package ca.etsmtl.taf.testrail.service.manager.query;


import ca.etsmtl.taf.testrail.service.manager.saving.DataResponseFromTR;

interface QueryTestRail {
    /*
    * Interface that contains common requests about TestRail data
    * Those methods are implemented in the classes that will query TestRail
    * */


    DataResponseFromTR add(String name);

    DataResponseFromTR delete(int projectId);
}