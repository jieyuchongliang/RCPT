package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/21.
 * 类描述：
 */

public class RecruitFragmentDataBean {


    private List<SchoolListBean> schoolList;
    private List<RecruitBean> recruit;

    public List<SchoolListBean> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolListBean> schoolList) {
        this.schoolList = schoolList;
    }

    public List<RecruitBean> getRecruit() {
        return recruit;
    }

    public void setRecruit(List<RecruitBean> recruit) {
        this.recruit = recruit;
    }

    public static class SchoolListBean {
        /**
         * schoolLog : uploadFiles/registerImg/7e54f36c853140e08b003509de467fc4.jpg
         * website : http://www.sdu.edu.cn/
         * creationTime : 1901年02月01日
         * schoolId : 29e341b37679d9eb
         * schoolName : 山东大学
         */

        private String schoolLog;
        private String website;
        private String creationTime;
        private String schoolId;
        private String schoolName;

        public String getSchoolLog() {
            return schoolLog;
        }

        public void setSchoolLog(String schoolLog) {
            this.schoolLog = schoolLog;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }
    }

    public static class RecruitBean {
        /**
         * employmentId : 42
         * TITLE : 盘点面试中自我介绍的3大语言技巧！
         * guideThumbnail :
         * value : 自我介绍
         * updateTimeStamp : 2017-02-23
         * guideContent : <p><span style="font-size: 12pt; line-height: 1;">从投简历到得到offer，还有一段很艰难、最有挑战性的必经之路：面试!有很多同学留言提问，除了问简历技巧，就是面试技巧了。因此，记者今天整理一份面试成功的10大绝招，助大家一臂之力，应聘成功。</span></p><p style="text-align:justify;">　　<strong>一、面试前准备</strong></p><p style="text-align:justify;">　　(绝招关键词：介绍+预演+暗示+清单)</p><p style="text-align:justify;">　　1、自我介绍</p><p style="text-align:justify;">　　准备不同时间版本的1 分钟、3 分钟的自我介绍。反复练习，并脱稿，但不是背诵。因为背的话自我介绍就显得十分的生硬，一定要用自然的语言将自我介绍说出来。</p><p style="text-align:justify;">　　2、预演</p><p style="text-align:justify;">　　搜索一些你应聘的公司和职位的面经。在宿舍里、家里，先自己预演下，可能会被问及的各种问题和答案。即使你不能猜中所有问题——当然HR也不会面面俱到、一直不停的提问，最关键词的只有那么几个。思考问题的过程会让你减轻紧张而且在面试时心里有底。</p><p style="text-align:justify;">　　3、积极自我暗示</p><p style="text-align:justify;">　　在群面的时候，我们可能会因为别人的发言而扰乱了自己的思路。这时候千万不要乱，适当的做深呼吸调整一下自己，做到沉着冷静，整理好自己的思路，努力回忆自己所学和所掌握的知识，给自己积极的心理暗示，一句话：相信自己能行!你是最棒的。</p><p style="text-align:justify;">　　4、优点清单</p><p style="text-align:justify;">　　自制一份自己的优点检查表，这样你会了解自己比想像中有着更多的优点，充分掌握了自己的优点才能在面试的时候表现的比别人更自信：</p><p style="text-align:justify;">　　(1)人格上的优点：有热心，有说服力，有勇气，坦白，诚实，公平，幽默感强，表现自然，有口才，思想开放，有趣，弹性大，负责任，有号召力，活泼，内省，温和，热情，整洁。</p><p style="text-align:justify;">　　(2)智能上的优点：善于分析，善于掌握观念，善于思考，知觉敏锐，聪明，智慧， 反应快，语言能力强，有良好的逻辑推理能力。</p><p style="text-align:justify;">　　(3)美感上的优点：对颜色敏感，对设计敏感，有创意，有想像力，能发明，能即兴发挥，机智，懂得配置家具，懂得选衣服，能安排食物和花卉，懂园艺，能弹奏乐器，能绘图，能唱歌，能演戏，能做手工艺活，能跳舞，爱好广泛。</p><p style="text-align:justify;">　　(4)情绪上的优点：温暖，敏感，关心别人，有同情心，能针对别人的需要做出适当的反应，慷慨，慈善，能鼓舞别人的信心，了解别人，照顾别人、体贴，考虑周到， 接纳别人，支持别人，原谅别人，判断准确。</p><p style="text-align:justify;">　　(5)体能上的优点：平衡能力强，体力好，有忍耐力，协调性好，动作敏捷，具备竞赛的精神，双手灵活，体形良好，力量大。</p><p><br/></p>
         */

        private String employmentId;
        private String TITLE;
        private String guideThumbnail;
        private String value;
        private String updateTimeStamp;
        private String guideContent;

        public String getEmploymentId() {
            return employmentId;
        }

        public void setEmploymentId(String employmentId) {
            this.employmentId = employmentId;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getGuideThumbnail() {
            return guideThumbnail;
        }

        public void setGuideThumbnail(String guideThumbnail) {
            this.guideThumbnail = guideThumbnail;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUpdateTimeStamp() {
            return updateTimeStamp;
        }

        public void setUpdateTimeStamp(String updateTimeStamp) {
            this.updateTimeStamp = updateTimeStamp;
        }

        public String getGuideContent() {
            return guideContent;
        }

        public void setGuideContent(String guideContent) {
            this.guideContent = guideContent;
        }
    }
}
