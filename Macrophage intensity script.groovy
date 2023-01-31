//set image type
setImageType('BRIGHTFIELD_H_DAB');
//select annotations
selectAnnotations();
//merge annotations
mergeSelectedAnnotations()

//average intensity quantification for merged annotations
runPlugin('qupath.lib.algorithms.IntensityFeaturesPlugin', '{"pixelSizeMicrons":1.0,"region":"ROI","tileSizeMicrons":15.0,"colorOD":true,"colorStain1":false,"colorStain2":true,"colorStain3":false,"colorRed":false,"colorGreen":false,"colorBlue":false,"colorHue":false,"colorSaturation":false,"colorBrightness":false,"doMean":true,"doStdDev":false,"doMinMax":false,"doMedian":true,"doHaralick":false,"haralickDistance":1,"haralickBins":32}')

//get key and values for annotations
def annotations = getAnnotationObjects()

//get individual key for intensity quantification
for (annotation in getAnnotationObjects()){
x = measurement(annotation, "ROI: 1.00 µm per pixel: OD Sum: Mean")
}

//get pre-saved or pre-generated Macrophage file and write to it
//**IMPORTANT** You must include your own path directory below for the script to function properly
def file = new File('YOUR PATHNAME HERE/Macrophages.json')
def newConfig = file.text.replaceAll(' 0.0', x.toString())

//Write new file to existing Config
//**IMPORTANT** You must include your own path directory below for the script to function properly
new File('YOUR PATHNAME HERE/LiverQuantM.json').write(newConfig, "utf-8")