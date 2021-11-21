package warikan

class Warikan(){

}

case class 店舗(val 請求金額: BigDecimal){
}

case class 参加者(val UUID: 参加者UUID, val 負担割合: 負担割合)

case class 参加者UUID(var 数値: Int){
    def 参加者UUIDを取得する: String = "UUID20211120" + 数値.toString
}

case class 負担割合(val 負担割合: BigDecimal)

case class 割り勘計算アプリ(){
    def 個人の負担金額を計算する(個人の負担割合: BigDecimal, 店舗側請求金額: 店舗): BigDecimal = {
        var 切り捨て前個人の負担金額: BigDecimal = 個人の負担割合 * 店舗側請求金額.請求金額
        return 切り捨て前個人の負担金額.setScale(0, scala.math.BigDecimal.RoundingMode.FLOOR)
    }
}