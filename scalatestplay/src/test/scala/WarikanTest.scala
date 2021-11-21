package warikan

import org.scalatest.FunSuite
import scala.collection.mutable._

class WarikanTest extends FunSuite {

    test("店舗側の請求金額を取得する"){
        val 請求金額: Int = 20000
        val 店舗 = new 店舗(請求金額)

        assert(店舗.請求金額 == 20000)
    }

    test("個人を取得する"){
        val 参加者: 参加者 = new 参加者(参加者UUID(1), 負担割合(1))
        val テスト参加者: 参加者 = new 参加者(参加者UUID(1), 負担割合(1))
        assert(参加者 == テスト参加者)
    }

    test("個人の負担金額を計算する"){
         val 個人の負担割合 = 1.0/2.0
         val 店舗 = new 店舗(20000.0)
         val 割り勘計算アプリ = new 割り勘計算アプリ
         val 個人の負担金額: BigDecimal = 割り勘計算アプリ.個人の負担金額を計算する(個人の負担割合, 店舗)
         assert(個人の負担金額 == 10000.0)
    }

    test("個人の負担額合計と店舗側請求金額の差分を出す"){
        val 田中さん: 参加者 = new 参加者(参加者UUID(1), 負担割合(1.0))
        val 佐藤さん: 参加者 = new 参加者(参加者UUID(2), 負担割合(2.0))
        val 武藤さん: 参加者 = new 参加者(参加者UUID(3), 負担割合(3.0))
        val 参加者一覧: Set[参加者] = Set(田中さん, 佐藤さん, 武藤さん)

        var 負担割合合計値: BigDecimal = 0.0
        for(個別参加者 <- 参加者一覧){
            負担割合合計値 += 個別参加者.負担割合.負担割合
        }

        val 請求金額: BigDecimal = 20000.0
        val 店舗 = new 店舗(請求金額)

        val 個別負担金額の箱: Buffer[BigDecimal] = Buffer()
        val 割り勘計算アプリ: 割り勘計算アプリ = new 割り勘計算アプリ
        for(個別参加者 <- 参加者一覧){
            val 個別負担割合: BigDecimal = 個別参加者.負担割合.負担割合 / 負担割合合計値
            個別負担金額の箱 += 割り勘計算アプリ.個人の負担金額を計算する(個別負担割合, 店舗)
        }
        
        var 個別負担金額合計: BigDecimal = 0.0
        for(個別負担金額 <- 個別負担金額の箱){
            個別負担金額合計 = 個別負担金額合計 + 個別負担金額
        }

        val 差額 = 店舗.請求金額 - 個別負担金額合計

        assert(差額 == 1.0)

    }
}