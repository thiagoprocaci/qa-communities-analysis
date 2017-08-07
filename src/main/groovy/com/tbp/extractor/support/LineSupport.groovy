package com.tbp.extractor.support


class LineSupport {

    String prepareLine(String line) {
        if(line == null) {
            return null
        }
        String lineAux = line.toString().trim()
        if(lineAux.startsWith("<row")) {
            return lineAux
        }
        return null
    }

}
