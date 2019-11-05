# business
### 一、主要的两种过滤系统
1、正向匹配：ForwardMatchSensitiveFilter  
2、基于分词：SegmentSensitiveFilter  

### 二、三种常用的匹配算法
1、HashMap暴力匹配：AnotherHashMapMatcher  
2、TierTree前缀树匹配：TierTreeMatcher  
3、BloomFilter：BloomFilterMatcher  

### 三、推荐组合

SegmentSensitiveFilter + TierTreeMatcher > BloomFilterMatcher > AnotherHashMapMatcher  

ForwardMatchSensitiveFilter + TierTreeMatcher > AnotherHashMapMatcher > BloomFilterMatcher  

