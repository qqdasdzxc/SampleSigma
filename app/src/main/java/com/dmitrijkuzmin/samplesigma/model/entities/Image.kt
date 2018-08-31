package com.dmitrijkuzmin.samplesigma.model.entities

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image")
class Image (@field:Element var link: String = "",
             @field:Element var url: String = "",
             @field:Element var title: String = "")