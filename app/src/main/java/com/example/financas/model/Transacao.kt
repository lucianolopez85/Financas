package com.example.financas.model

import java.math.BigDecimal
import java.util.Calendar

class Transacao (val valor: BigDecimal,
                 val categoria: String,
                 val tipo: Tipo,
                 val data: Calendar)