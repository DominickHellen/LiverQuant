//This .Groovy script is for automated batch detection of macrophages within liver sections.//

//This line of code is placed before and after analyzing each slide//
//It is used to pause the current thread for 100 milliseconds//
//It also clears accumulated RAM for batch projects//
Thread.sleep(100)
javafx.application.Platform.runLater {
    getCurrentViewer().getImageRegionStore().cache.clear()
    System.gc()
}
Thread.sleep(100)

//Allows Qupath to make decisions for positive staining based on DAB (brown) staining//
setImageType('BRIGHTFIELD_H_DAB');

//Selelcts annotations on each slide//
selectAnnotations();

//Creates objects within each annotation based on the threshold set for 'Macrophages' classifier//
createDetectionsFromPixelClassifier("Macrophages", 5.0, 0.0, "SPLIT", "DELETE_EXISTING")

//Clears RAM and thread after each slide//
Thread.sleep(100)
javafx.application.Platform.runLater {
    getCurrentViewer().getImageRegionStore().cache.clear()
    System.gc()
}
Thread.sleep(100)


