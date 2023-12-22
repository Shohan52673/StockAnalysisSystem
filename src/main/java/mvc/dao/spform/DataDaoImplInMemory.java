package mvc.dao.spform;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import mvc.bean.spform.EducationData;
import mvc.bean.spform.InterestData;
import mvc.bean.spform.SexData;

@Repository
public class DataDaoImplInMemory implements DataDao {

	@Override
    public List<EducationData> findAllEducationDatas() {
        return Arrays.asList(
            EducationData.builder().id(1).name("小學").build(),
            EducationData.builder().id(2).name("國中").build(),
            EducationData.builder().id(3).name("高中").build(),
            EducationData.builder().id(4).name("大學").build(),
            EducationData.builder().id(5).name("研究所").build()
        );
    }

	
	
//    @Override
//    public List<EducationData> findAllEducationDatas() {
//        return Arrays.asList(
//                new EducationData(1, "小學"),
//                new EducationData(2, "國中"),
//                new EducationData(3, "高中"),
//                new EducationData(4, "大學"),
//                new EducationData(5, "研究所"));
//    }

    @Override
    public Optional<EducationData> getEducationDataById(Integer id) {
        return findAllEducationDatas().stream().filter(edu -> edu.getId().equals(id)).findFirst();
    }

    @Override
    public List<SexData> findAllSexDatas() {
        return Arrays.asList(SexData.builder().id(1).name("男").build(),
        					 SexData.builder().id(2).name("女").build());
    }

    @Override
    public Optional<SexData> getSexDataById(Integer id) {
        return findAllSexDatas().stream().filter(sex -> sex.getId().equals(id)).findFirst();
    }

    
    
    @Override
    public List<InterestData> finAllInterestDatas() {
        return Arrays.asList(InterestData.builder().id(1).name("爬山").build(),
        					 InterestData.builder().id(2).name("音樂").build(),
        					 InterestData.builder().id(3).name("看書").build(),
        					 InterestData.builder().id(4).name("刺繡").build(),
        					 InterestData.builder().id(5).name("國畫").build(),
        					 InterestData.builder().id(6).name("FPV ✈").build());
         
    }

    @Override
    public Optional<InterestData> getInterestDataById(Integer id) {
        return finAllInterestDatas().stream().filter(interest -> interest.getId().equals(id)).findFirst();
    }
}
