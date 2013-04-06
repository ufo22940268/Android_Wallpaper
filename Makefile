all:
	ant debug install && adb shell "am start -a hongbosb.test"
