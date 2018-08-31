package com.dmitrijkuzmin.samplesigma.model.entities

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "guid")
class Guid (@field:Attribute var isPermaLink: String = "")