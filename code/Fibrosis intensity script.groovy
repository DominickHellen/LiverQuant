//set image type
setImageType('BRIGHTFIELD_OTHER');
//select annotations
selectAnnotations();
//merge annotations
mergeSelectedAnnotations()

//average intensity quantification for merged annotations
runPlugin('qupath.lib.algorithms.IntensityFeaturesPlugin', '{"pixelSizeMicrons":1.0,"region":"ROI","tileSizeMicrons":25.0,"colorOD":false,"colorStain1":false,"colorStain2":false,"colorStain3":true,"colorRed":false,"colorGreen":false,"colorBlue":false,"colorHue":false,"colorSaturation":false,"colorBrightness":false,"doMean":true,"doStdDev":false,"doMinMax":false,"doMedian":false,"doHaralick":false,"haralickDistance":1,"haralickBins":32}')

//get key and values for annotations
def annotations = getAnnotationObjects()

//get individual key for intensity quantification
for (annotation in getAnnotationObjects()){
x = measurement(annotation, "ROI: 1.00 µm per pixel: Residual: Mean")
}
//optimization for intensity quantification
//keep this
z = -(x*0.1)

//get pre-saved or pre-generated Fibrosis file and write to it
//**IMPORTANT** You must include your own path directory below for the script to function properly
def file = new File('YOUR PATHNAME GOES HERE/Fibrosis.json')
def newConfig = file.text.replaceAll(' 0.0', z.toString())

//Write new file to existing Config
//**IMPORTANT** You must include your own path directory below for the script to function properly
new File('YOUR PATHNAME GOES HERE/LiverQuantF.json').write(newConfig, "utf-8")
