package com.app.network.data.datasourcesImpl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.network.NetworkConstants
import com.app.network.data.models.Article
import com.app.network.data.models.NewsArticle
import com.app.network.data.services.NewsService
import com.app.network.domain.datasources.NewsNetworkDataSource
import com.google.gson.Gson
import javax.inject.Inject

class NewsNetworkDataSourceImpl @Inject constructor( var service: NewsService) : NewsNetworkDataSource,
    PagingSource<Int, Article>() {
    private var selectedCountry: String = "in"
    private var pageSize: Int = NetworkConstants.DEFAULT_PAGE_LIMIT
    private var pageNumber: Int = 1
    fun setApiCallData(country: String, pageSize: Int, pageNumber: Int) {
        this.selectedCountry = country
        this.pageNumber = pageNumber
        this.pageSize = pageSize
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        return try {
        val page = params.key ?: 1
//            val response = newsService.getNewsData(country = selectedCountry, pageSize, pageNumber)
//            val articles = response.body()?.articles?.mapNotNull { it } ?: arrayListOf()
//            if (response.isSuccessful.not()) {
//                LoadResult.Error(Throwable(message = "Error"))
//            } else if (articles.isEmpty()) {
//                LoadResult.Error(Throwable(message = "Error"))
//            } else {
//                LoadResult.Page(
//                    data = articles,
//                    prevKey = if (page == 1) null else page.minus(1),
//                    nextKey = page.plus(1),
//                )
//            }
//
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
        val articles = getNewsData()?.articles?.mapNotNull { it } ?: arrayListOf()
        return LoadResult.Page(
            data = articles,
            prevKey = if (page == 1) null else page.minus(1),
            nextKey = page.plus(1)
        )
    }

    override suspend fun getNewsData(): NewsArticle? {
        val data = "{\n" +
                "  \"articles\": [\n" +
                "    {\n" +
                "      \"author\": \"Snehashish Roy\",\n" +
                "      \"content\": \"Bahujan Samaj Party (BSP) MP Danish Ali on Friday alleged that the content of Lok Sabha's ethics panel report against TMC MP Mahua Moitra on the cash-for-query case had been written somewhere else, o… [+1704 chars]\",\n" +
                "      \"description\": \"The report recommended expulsion of the TMC MP for accepting ‘illegal gratifications’ from industrialist in order to raise questions in the Lok Sabha. | Latest News India\",\n" +
                "      \"publishedAt\": \"2023-11-11T01:07:00Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"Hindustan Times\"\n" +
                "      },\n" +
                "      \"title\": \"Danish Ali says ethics panel's ‘script written somewhere else’; snubs BJP MP - Hindustan Times\",\n" +
                "      \"url\": \"https://www.hindustantimes.com/india-news/bsps-danish-ali-says-ethics-panels-script-written-somewhere-else-snubs-bjp-mp-101699663074855.html\",\n" +
                "      \"urlToImage\": \"https://www.hindustantimes.com/ht-img/img/2023/11/11/1600x900/PTI09-29-2023-000259A-0_1699664636152_1699664661402.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"ESPNcricinfo staff\",\n" +
                "      \"content\": \"Harmison: Even if some England careers finish, they have been absolutely outstanding\",\n" +
                "      \"description\": \"South Africa had some nervy moments beating Afghanistan and also suffered an injury scare to their captain\",\n" +
                "      \"publishedAt\": \"2023-11-11T00:01:13Z\",\n" +
                "      \"source\": {\n" +
                "        \"id\": \"espn-cric-info\",\n" +
                "        \"name\": \"ESPN Cric Info\"\n" +
                "      },\n" +
                "      \"title\": \"ODI World Cup digest: Pakistan need a miracle; Australia seeking seven in a row - ESPNcricinfo\",\n" +
                "      \"url\": \"https://www.espncricinfo.com/story/odi-world-cup-digest-pakistan-need-a-miracle-australia-seeking-seven-wins-in-a-row-1408250\",\n" +
                "      \"urlToImage\": \"https://img1.hscicdn.com/image/upload/f_auto/lsci/db/PICTURES/CMS/371000/371037.6.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"The Hindu\",\n" +
                "      \"publishedAt\": \"2023-11-10T22:44:00Z\",\n" +
                "      \"source\": {\n" +
                "        \"id\": \"the-hindu\",\n" +
                "        \"name\": \"The Hindu\"\n" +
                "      },\n" +
                "      \"title\": \"U.S. approves first vaccine against chikungunya virus - The Hindu\",\n" +
                "      \"url\": \"https://www.thehindu.com/news/international/us-approves-first-vaccine-against-chikungunya-virus/article67521533.ece\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"content\": \"Earlier, Israel had claimed that more than 1,400 Israeli were killed in Hamas attacks on October 7.\\r\\nJerusalem: Israel on Friday revised down the death toll of last month's Hamas attacks to about 1,2… [+6639 chars]\",\n" +
                "      \"description\": \"Israel on Friday revised down the death toll of last month's Hamas attacks to about 1,200 as it continued its assault on Gaza, forcing thousands of Palestinians to flee south to escape the destruction in the city.\",\n" +
                "      \"publishedAt\": \"2023-11-10T21:55:48Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"NDTV News\"\n" +
                "      },\n" +
                "      \"title\": \"Israel Revises Down October 7 Hamas Attack Death Count To 1,200 - NDTV\",\n" +
                "      \"url\": \"https://www.ndtv.com/world-news/israel-revises-down-death-count-from-october-7-attack-to-around-1-200-4565870\",\n" +
                "      \"urlToImage\": \"https://c.ndtvimg.com/2023-11/inq156p_gaza-reuters_625x300_08_November_23.jpeg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"Gina Mauro\",\n" +
                "      \"content\": \"With a reflection on the past paradigm-changing studies done in advanced prostate cancer, Howard I. Scher, MD, emphasized that the field needs to shift focus to earlier-stage settings, spotlighting i… [+9073 chars]\",\n" +
                "      \"description\": \"Howard I. Scher, MD, sheds light on various research programs he has led or has been heavily involved with that have helped change the course of prostate cancer treatment over the years.\",\n" +
                "      \"publishedAt\": \"2023-11-10T21:28:17Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"OncLive\"\n" +
                "      },\n" +
                "      \"title\": \"Reflecting on Advances Made in High-risk, Low-Grade Prostate Cancer Management - OncLive\",\n" +
                "      \"url\": \"https://www.onclive.com/view/reflecting-on-advances-made-in-high-risk-low-grade-prostate-cancer-management\",\n" +
                "      \"urlToImage\": \"https://cdn.sanity.io/images/0vv8moc6/onclive/67abe93f06faf7e8b08c58c7d8b1492f316abb0b-200x200.png?fit=crop&auto=format\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"Sportstar\",\n" +
                "      \"publishedAt\": \"2023-11-10T21:21:02Z\",\n" +
                "      \"source\": {\n" +
                "        \"id\": \"the-hindu\",\n" +
                "        \"name\": \"The Hindu\"\n" +
                "      },\n" +
                "      \"title\": \"Sri Lanka Cricket suspended by ICC over breach of regulations - Sportstar\",\n" +
                "      \"url\": \"https://sportstar.thehindu.com/cricket/sri-lanka-cricket-suspended-by-icc-breach-of-regulations-government-interference-cricket-world-cup-news/article67522229.ece\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"description\": \"SpaceX launched its Dragon spacecraft to the International Space Station on Thursday Nov. 9, in its 29th mission to resupply the ISS. It's due to automatical...\",\n" +
                "      \"publishedAt\": \"2023-11-10T19:30:06Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"YouTube\"\n" +
                "      },\n" +
                "      \"title\": \"SpaceX sends 29th spacecraft to International Space Station - CGTN America\",\n" +
                "      \"url\": \"https://www.youtube.com/watch?v=twGIuHvV3cs\",\n" +
                "      \"urlToImage\": \"https://i.ytimg.com/vi/twGIuHvV3cs/maxresdefault.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"CricTracker Staff\",\n" +
                "      \"content\": \"Australia (AUS) will take on Bangladesh (BAN) in the 43rd game of the ODI World Cup 2023 on Saturday (November 11) at the Maharashtra International Cricket Stadium in Pune. Australia have won six out… [+1514 chars]\",\n" +
                "      \"description\": \"AUS vs BAN Dream11 Team Today - Check out Australia vs. Bangladesh Dream11 prediction, playing 11, World Cup fantasy league, &amp; more updates for the 37th match of 2023 ICC Cricket World Cup only on CricTracker.Com\",\n" +
                "      \"publishedAt\": \"2023-11-10T19:30:00Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"Crictracker.com\"\n" +
                "      },\n" +
                "      \"title\": \"AUS vs BAN Dream11 Prediction, ODI World Cup 2023, Match 43: Australia vs Bangladesh playing XI, fantasy team today's, squads, and Pitch Report - CricTracker\",\n" +
                "      \"url\": \"https://www.crictracker.com/fantasy-cricket-tips/dream11-aus-vs-ban-dream11-prediction-world-cup-fantasy-team-todays-playing-xi-squads-for-match-43-034/\",\n" +
                "      \"urlToImage\": \"https://media.crictracker.com/media/attachments/1699368519114_Australia.jpeg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"Sachin Parashar\",\n" +
                "      \"description\": \"India News: India and the US held talks in their 5th 2+2 ministerial to strengthen their partnership in defense, security, trade, and technology. They reaffirmed\",\n" +
                "      \"publishedAt\": \"2023-11-10T18:51:00Z\",\n" +
                "      \"source\": {\n" +
                "        \"id\": \"the-times-of-india\",\n" +
                "        \"name\": \"The Times of India\"\n" +
                "      },\n" +
                "      \"title\": \"India, US back Israel on terror, diss 'China’s aggression'; in a first, no mention of Pakistan in 2+2 joi - IndiaTimes\",\n" +
                "      \"url\": \"https://timesofindia.indiatimes.com/india/india-us-back-israel-on-terror-diss-chinas-aggression-in-a-first-no-mention-of-pakistan-in-22-joint-statement/articleshow/105133192.cms\",\n" +
                "      \"urlToImage\": \"https://static.toiimg.com/thumb/msid-105133194,width-1070,height-580,imgsize-53756,resizemode-75,overlay-toi_sw,pt-32,y_pad-40/photo.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"DC Correspondent\",\n" +
                "      \"content\": \"Police advised the general public to avoid roads from Panjagutta to Secunderabad via Begumpet as congestion is expected on this road and also in the roads surrounding Parade Grounds. (Image: DC)\",\n" +
                "      \"description\": \"Traffic Advisory Issued for Prime Minister Modi's Hyderabad Visit\",\n" +
                "      \"publishedAt\": \"2023-11-10T18:46:09Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"Deccan Chronicle\"\n" +
                "      },\n" +
                "      \"title\": \"Traffic Advisory Issued for Prime Minister Modi's Hyderabad Visit - Deccan Chronicle\",\n" +
                "      \"url\": \"https://www.deccanchronicle.com/nation/current-affairs/101123/traffic-advisory-issued-for-prime-minister-modis-hyderabad-visit.html\",\n" +
                "      \"urlToImage\": \"https://s3.ap-southeast-1.amazonaws.com/images.deccanchronicle.com/dc-Cover-6qq7pvagdi5t9jqjok05gmle64-20231110182903.Medi.jpeg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"Shivangani Singh\",\n" +
                "      \"content\": \"The next morning, housemates wake up to the Bigg Boss anthem. Vicky makes fun of how Sana removed Navid from the race but he is still friends with Sana and later they get into a fight. On the other s… [+1177 chars]\",\n" +
                "      \"description\": \"Bigg Boss 17, Day 27 episode today, on Friday, 10 November 2023, was fun-filled and dramatic. Know what all happened inside the Bigg Boss House today and stay informed.\",\n" +
                "      \"publishedAt\": \"2023-11-10T18:33:36Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"The Quint\"\n" +
                "      },\n" +
                "      \"title\": \"Bigg Boss 17 Episode 27, Written Episode: Samarth & Sunny's Ugly Face Off - The Quint\",\n" +
                "      \"url\": \"https://www.thequint.com/entertainment/bigg-boss-17-episode-27-written-episode-samarth-sunnys-ugly-face-off\",\n" +
                "      \"urlToImage\": \"https://images.thequint.com/thequint%2F2023-10%2Fc2242a2d-e5c8-481e-b4a7-b32750a9f088%2FBigg_Boss_17.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"TIMESOFINDIA.COM\",\n" +
                "      \"content\": \"Who was Sam Manekshaw, the Indian Army Officer Vicky Kaushal is playing in Sam Bahadur?\",\n" +
                "      \"description\": \"India News: Indian-American singer Falu and Prime Minister Narendra Modi's collaboration on a song promoting the benefits of millets has been nominated for a Gram\",\n" +
                "      \"publishedAt\": \"2023-11-10T18:21:00Z\",\n" +
                "      \"source\": {\n" +
                "        \"id\": \"the-times-of-india\",\n" +
                "        \"name\": \"The Times of India\"\n" +
                "      },\n" +
                "      \"title\": \"Song on millets featuring PM Modi nominated for grammy - IndiaTimes\",\n" +
                "      \"url\": \"https://timesofindia.indiatimes.com/india/song-on-millets-featuring-pm-modi-nominated-for-grammy/articleshow/105132973.cms\",\n" +
                "      \"urlToImage\": \"https://static.toiimg.com/thumb/msid-105133062,width-1070,height-580,imgsize-38468,resizemode-75,overlay-toi_sw,pt-32,y_pad-40/photo.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"The Quint\",\n" +
                "      \"content\": \"Afghanistan became the fifth team to be eliminated from the semi-final sprint of the 2023 ICC World Cup, following their five-wicket defeat to South Africa today (10 November). \\r\\nEarlier, the quartet… [+192 chars]\",\n" +
                "      \"description\": \"ICC World Cup 2023: Semi-Final Qualification Scenarios – With Afghanistan being officially eliminated, we are now left with two contenders for one available slot, in New Zealand and Pakistan. Here's what each team needs.\",\n" +
                "      \"publishedAt\": \"2023-11-10T17:05:06Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"The Quint\"\n" +
                "      },\n" +
                "      \"title\": \"ICC World Cup 2023: Semi-Final Qualification Scenarios – What Each Team Needs - The Quint\",\n" +
                "      \"url\": \"https://www.thequint.com/sports/world-cup/icc-world-cup-2023-semi-final-qualification-scenarios-what-pakistan-new-zealand-need\",\n" +
                "      \"urlToImage\": \"https://images.thequint.com/thequint%2F2023-11%2F63f5ee9e-5797-4e64-b32d-295a84472a02%2FQualification_Scenarios.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"The Hindu\",\n" +
                "      \"publishedAt\": \"2023-11-10T17:05:00Z\",\n" +
                "      \"source\": {\n" +
                "        \"id\": \"the-hindu\",\n" +
                "        \"name\": \"The Hindu\"\n" +
                "      },\n" +
                "      \"title\": \"Canada police probe video warning not to fly Air India - The Hindu\",\n" +
                "      \"url\": \"https://www.thehindu.com/news/international/canada-police-probe-video-warning-not-to-fly-air-india/article67521494.ece\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"author\": \"The Financial Express\",\n" +
                "      \"publishedAt\": \"2023-11-10T15:25:12Z\",\n" +
                "      \"source\": {\n" +
                "        \"name\": \"Financial Express\"\n" +
                "      },\n" +
                "      \"title\": \"Chandrayaan-4 mission: How NASA and ESA will contribute to ISRO’S lunar project – Key details - The Financial Express\",\n" +
                "      \"url\": \"https://www.financialexpress.com/life/science-chandrayaan-4-mission-how-nasa-and-esa-will-contribute-to-isros-lunar-project-key-details-bkg-3304480/\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"ok\",\n" +
                "  \"totalResults\": 38\n" +
                "}"
        return Gson().fromJson(data, NewsArticle::class.java)
    }
}