package bcomposes.twitter

import twitter4j._
import twitter4j.TwitterFactory
import twitter4j.Twitter
import twitter4j.conf.ConfigurationBuilder

object StatusStreamer {
	def main(args: Array[String]) {
		object Util {
			val config = new ConfigurationBuilder()
								.setOAuthConsumerKey("<ConsumerKey>")
								.setOAuthConsumerSecret("<ConsumerSecretKey>")
								.setOAuthAccessToken("<AccessToken>")
								.setOAuthAccessTokenSecret("<AccessTokenSecret>")
								.build()
			def simpleStatusListener = new StatusListener() {
				def onStatus(status: Status) { println(status.getText) }
				def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
				def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
				def onException(ex: Exception) { ex.printStackTrace }
				def onScrubGeo(arg0: Long, arg1: Long) {}
				def onStallWarning(warning: StallWarning) {}
			}
		}
		
		val twitterStream = new TwitterStreamFactory(Util.config).getInstance
		twitterStream.addListener(Util.simpleStatusListener)
		twitterStream.sample
		Thread.sleep(2000)
		twitterStream.cleanUp
		twitterStream.shutdown	
	}
}
