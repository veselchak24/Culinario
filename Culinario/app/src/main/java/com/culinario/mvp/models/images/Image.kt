package com.culinario.mvp.models

/**
 * Интерфейс Image
 * @property Id идентификатор изображения.
 * @property Data данные изображения в виде массива байтов.
 */

interface Image {
     /**
      * Идентификатор изображения.
      */
     val Id: Int

     /**
      * Данные изображения в виде массива байтов.
      */
     val Data: ByteArray
}
