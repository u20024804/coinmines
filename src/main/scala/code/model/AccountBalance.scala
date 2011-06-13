package code
package model

import java.util.Date

import net.liftweb.mapper._
import net.liftweb.util._
import net.liftweb.common._

object AccountBalance extends AccountBalance with LongKeyedMetaMapper[AccountBalance] {
	override def dbTableName = "account_balances"
}

class AccountBalance extends LongKeyedMapper[AccountBalance] with IdPK {
	def getSingleton = AccountBalance

	object user extends MappedLongForeignKey(this, User) {
		override def dbIndexed_? = true
		override def dbNotNull_? = true
	}

	object sendAddress extends MappedString(this, 255) {
		override def dbIndexed_? = true
		override def dbNotNull_? = true
	}

	object balance extends MappedDecimal(this, java.math.MathContext.DECIMAL64, 8) {
		override def dbNotNull_? = true
	}

	object paid extends MappedBoolean(this) {
		override def dbIndexed_? = true
		override def dbNotNull_? = true
		override def defaultValue = false
	}

	object threshold extends MappedInt(this) {
		override def dbIndexed_? = true
		override def dbNotNull_? = true
		override def defaultValue = 0
	}

	object timestamp extends MappedDateTime(this) {
		override def dbNotNull_? = true
		def beforeCreate = this(new Date)
	}

}
