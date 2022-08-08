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

//Allows Qupath to make decisions for positive staining based on staining that is residual to DAB and hematoxylin//
setImageType('BRIGHTFIELD_OTHER');

//Selelcts annotations on each slide//
selectAnnotations();

//Quantifies positive pixel amount (Fibrotic septa) for entire all annotations//
addPixelClassifierMeasurements("Fibrosis", "Fibrosis")

//Clears RAM and thread after each slide//
Thread.sleep(100)
javafx.application.Platform.runLater {
    getCurrentViewer().getImageRegionStore().cache.clear()
    System.gc()
}
Thread.sleep(100)

