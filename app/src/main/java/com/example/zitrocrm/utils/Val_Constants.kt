package com.example.zitrocrm.utils

object Val_Constants {

    /**BaseURL-Productivo User : Luis = ZT<dn5-z**/
    //const val BASE_URL = "http://zitrocrm.odelnorte.mx:8083/"
    /**BaseURL-Pruebas  User : Luis = s4501**/
    const val BASE_URL = "http://pruebascrm.odelnorte.mx:8084/"
    //const val BASE_URL = "http://pruebascrm.odelnorte.mx:3000/"
    /***LOGIN*/
    const val API_LOGIN = "api/login"
    /**FILTER**/
    const val API_FILTER_REGIONES = "api/catalogos/regiones"
    const val API_FILTER_SALAS = "api/catalogos/salas"
    const val API_FILTER_CLIENTES = "api/catalogos/clientes"
    const val API_FILTER_OBJETIVOS_GENERALES = "/api/catalogos/salas/objetivosGenerales"
    const val API_FILTER_PROVEEDORES = "/api/catalogos/proveedoresByTipo"
    const val API_CATALOGOS_JUEGOS = "/api/catalogos/juegos"
    const val API_SALAS_FOLIO_TECNICO = "/api/salas/foliosTecnicos"
    const val API_SALAS_VISITAS_PROMOTORES_VISITA = "/api/salas/visitas/promotores"
    const val API_SALAS_COMPETENCIA_LIBRERIAS = "api/salas/competencia"
    const val API_SALAS_SUBJUEGOS = "api/catalogos/subjuegos"
    const val API_JUEGOS_TIPO_CARTONES = "api/catalogos/tipoCarton"
    const val API_FAMILIA_BINGO_JUEGOS = "api/catalogos/familiaBingo"
    const val API_ORDENES_CAMBIO = "api/maquinas/ordenesCambio/prioridades"
    const val API_ULTIMO_DO = "api/salas/visitas/promotores/ultimosRegistros"
    /**MUTABLE STATE LIST SALAS NEW REGISTRO**/
    const val ExpandAnimation = 300
    const val CollapseAnimation = 300
    const val FadeInAnimation = 300
    const val FadeOutAnimation = 300
}