package com.poly.lmsapp.ui.detail_doc.detail_quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.base.BaseActivity;
import com.poly.lmsapp.commons.network.Client;
import com.poly.lmsapp.commons.resource.KeyResource;
import com.poly.lmsapp.commons.utils.*;
import com.poly.lmsapp.model.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DetailAssignmentActivity extends BaseActivity {
    private Intent intent;
    private DocumentType assignment;
    private TextView mTvSchedule;
    private CardView mCvSchedule;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private TextView mTvTimeResult;
    private TextView mTvSubmission;
    private CardView mCvSubmisstion;
    private RecyclerView mRvFile;
    private Button mBtnPickFile;
    private Button mBtnLamBai;
    private LinearLayout mViewCacCauHoi;
    private TextView mTvThuTu;
    private TextView mTvCauHoi;

    AnswerAdapter answerAdapter;
    private Button mBtnPrev;
    private Button mBtnNext;
    private Button mBtnFinish;
    private LinearLayout mViewKetQua;
    private TextView mTvKetQua;
    private TextView mTvTime;
    private TextView mTvDescription;
    private InfoQuiz infoQuiz;
    private int elementIndex = 0;
    private LinearLayout mViewBatDauLamBai;
    private LinearLayout mViewNutQuiz;

    private ArrayList<Question> listData = new ArrayList<>();
    int point = 0;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_detail_quiz);
    }

    @Override
    public void createView() {

        intent = getIntent();
        assignment = (DocumentType) Utils.jsonDecode(intent.getStringExtra(KeyResource.OBJECT_DOCUMENT), DocumentType.class);
        setTbDrawable(R.drawable.bg_gradient);
        setToolbarTitle(assignment.getName());
        initView();
        if (assignment != null) {
            if (assignment.getType() != null && assignment.getType().equals("QUIZ")) {
                mViewNutQuiz.setVisibility(View.GONE);
                visibleUploadFile(View.GONE);
                mCvSchedule.setVisibility(View.GONE);


                checkStatusQuiz();


                mBtnFinish.setOnClickListener(view -> {
                    mViewCacCauHoi.setVisibility(View.GONE);
                    mViewNutQuiz.setVisibility(View.GONE);
                    mViewKetQua.setVisibility(View.VISIBLE);
                });
                mBtnPrev.setVisibility(View.GONE);
                mBtnNext.setOnClickListener(view -> {
                    if (elementIndex < listData.size() - 1) {
                        setUpCauHoi(elementIndex + 1);
                        mBtnPrev.setVisibility(View.VISIBLE);
                        if (elementIndex + 1 >= listData.size()) {
                            mBtnNext.setVisibility(View.GONE);
                        }
                    } else {
                        mBtnNext.setVisibility(View.GONE);
                        mBtnPrev.setVisibility(View.VISIBLE);
                    }

                });

                mBtnPrev.setOnClickListener(view -> {
                    if (elementIndex > 0) {
                        setUpCauHoi(elementIndex - 1);
                        mBtnNext.setVisibility(View.VISIBLE);
                        if (elementIndex <= 0) {
                            mBtnPrev.setVisibility(View.GONE);
                        }
                    } else {
                        mBtnNext.setVisibility(View.VISIBLE);
                        mBtnPrev.setVisibility(View.GONE);
                    }

                });

                mBtnFinish.setOnClickListener(view -> {
                    updatePoint();
                });
            } else {
                mViewNutQuiz.setVisibility(View.GONE);
                visibleUploadFile(View.GONE);
                mViewBatDauLamBai.setVisibility(View.GONE);
                fetchData();
                mBtnPickFile.setOnClickListener(view -> {
                    FilePicker.setActivity(DetailAssignmentActivity.this);
                    FilePicker.showFilePicker();
                });
            }
        }


    }

    private void checkStatusQuiz() {
        showLoading(true);
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_DOCUMENT_TYPE, EnviromentSingleton.getEnviromentSingleton().getIdDocumentType());
        Client.getInstance().getInfoQUiz(map).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                if (baseResponse != null) {
                    //Chưa làm bài
                    if (baseResponse.getError().getCode() == 0) {


                        mCvSchedule.setVisibility(View.VISIBLE);
                        String time = DateTimeUtils.toDateFormat(assignment.getStartTime(), DateTimeUtils.SERVER_DATE_2, DateTimeUtils.TIME_DATE);
                        String etime = DateTimeUtils.toDateFormat(assignment.getEndTime(), DateTimeUtils.SERVER_DATE_2, DateTimeUtils.TIME_DATE);
                        mTvStartTime.setText(time);
                        mTvEndTime.setText(etime);
                        mTvDescription.setText(assignment.getDescription());
                        if (!DateTimeUtils.afterNow(DateTimeUtils.parseDate(assignment.getEndTime(), DateTimeUtils.SERVER_DATE_2))) {
                            mViewBatDauLamBai.setVisibility(View.GONE);
                            mTvTimeResult.setText("Đã hết hạn");
                            mTvTimeResult.setTextColor(Color.RED);
                        } else {
                            mViewBatDauLamBai.setVisibility(View.VISIBLE);
                            mTvTimeResult.setText(DateTimeUtils.diffDate(DateTimeUtils.parseDate(assignment.getEndTime(), DateTimeUtils.SERVER_DATE_2)));

                        }
                        mBtnLamBai.setOnClickListener(view -> {
                            mViewBatDauLamBai.setVisibility(View.GONE);
                            mViewCacCauHoi.setVisibility(View.VISIBLE);
                            mViewNutQuiz.setVisibility(View.GONE);
                            updateStatusQuiz();
                        });
                        //đã hết giờ làm bài
                    } else if (baseResponse.getError().getCode() == 1) {
                        mViewBatDauLamBai.setVisibility(View.GONE);
                        infoQuiz = (InfoQuiz) Utils.jsonDecode(baseResponse.getData(), InfoQuiz.class);
                        mTvTimeResult.setText("Đã hết thời gian làm bài.");
                        mTvTimeResult.setTextColor(getResources().getColor(R.color.red));
                        mViewKetQua.setVisibility(View.VISIBLE);
                        mViewCacCauHoi.setVisibility(View.GONE);
                        mViewNutQuiz.setVisibility(View.GONE);
                        mTvKetQua.setText("Bạn đạt điểm: " + infoQuiz.getPoint() +" điểm");
                        //đang làm bài
                    } else if (baseResponse.getError().getCode() == 2) {
                        infoQuiz = (InfoQuiz) Utils.jsonDecode(baseResponse.getData(), InfoQuiz.class);
                        getDataQuestion();

                    } else if (baseResponse.getError().getCode() == 3) {
                        mTvTimeResult.setText("Đã hết thời gian làm bài. Bạn chưa làm quiz này");
                        mTvTimeResult.setTextColor(getResources().getColor(R.color.red));
                        mViewBatDauLamBai.setVisibility(View.GONE);

                    }

                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    private void updateStatusQuiz() {
        showLoading(true);
        Client.getInstance().updateInfoQUiz(new InfoQuiz(EnviromentSingleton.getEnviromentSingleton().getIdDocumentType())).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    infoQuiz = (InfoQuiz) Utils.jsonDecode(baseResponse.getData(), InfoQuiz.class);
                    getDataQuestion();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }


    private void countDown() {
        Date event_date = DateTimeUtils.parseDate(infoQuiz.getEndTime(), DateTimeUtils.SERVER_DATE);
        Log.d("AAAAAAA", "countDown: " + DateTimeUtils.apartDate(event_date));
        new CountDownTimer(DateTimeUtils.apartDate(event_date), 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000 % 60;
                long minutes = millisUntilFinished / (60 * 1000) % 60;
                mTvTime.setText("" + (minutes) + ":" + (seconds));
            }

            public void onFinish() {
                mTvTime.setText("Đã hết giờ");
                updatePoint();

            }
        }.start();
    }

    private void getDataQuestion() {
        showLoading(false);
        Map<String, Object> map = new HashMap<>();
        map.put("idDanhMuc", EnviromentSingleton.getEnviromentSingleton().getIdDocumentType());
        Client.getInstance().getListQuestion(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();

                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    visibleUploadFile(View.GONE);
                    mViewBatDauLamBai.setVisibility(View.GONE);
                    mViewCacCauHoi.setVisibility(View.VISIBLE);
                    mViewNutQuiz.setVisibility(View.VISIBLE);
                    basePageResponse.getData().forEach(o -> {
                        listData.add((Question) Utils.jsonDecode(o, Question.class));
                    });

                    if (listData.size() > 0) {
                        setUpCauHoi(0);
                    }
                    countDown();
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    String selectedValue = "";

    private void setUpCauHoi(int elementIndex) {
        mTvThuTu.setText("Câu thứ " + (elementIndex + 1) + " trên " + listData.size());
        mTvCauHoi.setText(listData.get(elementIndex).getContent());
//         answerAdapter = new AnswerAdapter(listData.get(elementIndex).getListCauTraLoi(), R.layout.item_answer);
//        mRvCauTraLoi.setAdapter(answerAdapter);

        RadioGroup radioGroup = new RadioGroup(this);


        for (int i = 0; i < listData.get(elementIndex).getListCauTraLoi().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(listData.get(elementIndex).getListCauTraLoi().get(i).getContent());
            radioGroup.addView(radioButton);

            int keyI = i;
            if (listData.get(elementIndex).getListCauTraLoi().get(i) != null) {
                radioButton.setChecked(listData.get(elementIndex).getListCauTraLoi().get(i).isChecked());
            }

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (radioButton.isChecked()) {
                        selectedValue = listData.get(elementIndex).getListCauTraLoi().get(keyI).getContent();
                        listData.get(elementIndex).getListCauTraLoi().get(keyI).setChecked(true);
                    }
                }
            });
        }
        if (mViewCacCauHoi.getChildCount() > 4)
            mViewCacCauHoi.removeViewAt(4);
        mViewCacCauHoi.addView(radioGroup);
        this.elementIndex = elementIndex;
    }

    private void updatePoint() {
        mViewCacCauHoi.setVisibility(View.GONE);
        showLoading(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            listData.forEach(e -> {
                e.getListCauTraLoi().forEach(t -> {
                    if (e.getIdDapAp() == t.getId()) {
                        point += (10 / listData.size());
                    }
                });
            });
        }
        Client.getInstance().updatePointInfoQUiz(new InfoQuiz(EnviromentSingleton.getEnviromentSingleton().getIdDocumentType(), point)).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                showLoading(false);
                mViewKetQua.setVisibility(View.VISIBLE);
                mViewCacCauHoi.setVisibility(View.GONE);
                mViewNutQuiz.setVisibility(View.GONE);
                checkStatusQuiz();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    Utils.showToast(DetailAssignmentActivity.this, "Bài làm đã được gửi");
                } else {
                    Utils.showToast(DetailAssignmentActivity.this, "Hệ thống bận");
                    mTvKetQua.setText("");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Utils.showToast(DetailAssignmentActivity.this, "Đã có lỗi xảy ra");
            }
        });
    }

    private void initView() {
        mTvSchedule = findViewById(R.id.tv_schedule);
        mCvSchedule = findViewById(R.id.cv_schedule);
        mTvStartTime = findViewById(R.id.tv_start_time);
        mTvEndTime = findViewById(R.id.tv_end_time);
        mTvTimeResult = findViewById(R.id.tv_time_result);
        mTvSubmission = findViewById(R.id.tv_submission);
        mCvSubmisstion = findViewById(R.id.cv_submisstion);
        mRvFile = findViewById(R.id.rv_file);
        mBtnPickFile = findViewById(R.id.btn_pick_file);
        mBtnLamBai = findViewById(R.id.btn_lam_bai);
        mViewCacCauHoi = findViewById(R.id.view_cac_cau_hoi);
        mTvThuTu = findViewById(R.id.tv_thu_tu);
        mTvCauHoi = findViewById(R.id.tv_cau_hoi);

        mBtnPrev = findViewById(R.id.btn_prev);
        mBtnNext = findViewById(R.id.btn_next);
        mBtnFinish = findViewById(R.id.btn_finish);
        mViewKetQua = findViewById(R.id.view_ket_qua);
        mTvKetQua = findViewById(R.id.tv_ket_qua);
        mTvDescription = findViewById(R.id.tv_description);
        mViewBatDauLamBai = findViewById(R.id.view_bat_dau_lam_bai);
        mViewNutQuiz = findViewById(R.id.view_nut_quiz);

        mTvTime = findViewById(R.id.tv_time);
    }

    @Override
    public void fetchData() {
        showLoading(true);
        Map<String, Object> map = new HashMap<>();
        map.put(KeyResource.ID_DOCUMENT_TYPE, assignment.getId());
        map.put(KeyResource.ID_USER, PersonSingleton.getInstance().getUser().getId());
        Client.getInstance().getListFileAttach(map).enqueue(new Callback<BaseResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                    BasePageResponse basePageResponse = (BasePageResponse) Utils.jsonDecode(baseResponse.getData(), BasePageResponse.class);
                    ArrayList<FileAttach> fileAttaches = new ArrayList<>();
                    basePageResponse.getData().forEach(o -> {
                        fileAttaches.add((FileAttach) Utils.jsonDecode(o, FileAttach.class));
                    });
                    FileAttachAdapter fileAttachAdapter = new FileAttachAdapter(fileAttaches, R.layout.item_file_attach);
                    mRvFile.setAdapter(fileAttachAdapter);
                    String time = DateTimeUtils.toDateFormat(assignment.getStartTime(), DateTimeUtils.SERVER_DATE_2, DateTimeUtils.TIME_DATE);
                    String etime = DateTimeUtils.toDateFormat(assignment.getEndTime(), DateTimeUtils.SERVER_DATE_2, DateTimeUtils.TIME_DATE);

                    mTvStartTime.setText(time);
                    mTvEndTime.setText(etime);
                    mTvDescription.setText(assignment.getDescription());
                    Date endDate = DateTimeUtils.parseDate(assignment.getEndTime(), DateTimeUtils.SERVER_DATE_2);
                    Date startDate = DateTimeUtils.parseDate(assignment.getStartTime(), DateTimeUtils.SERVER_DATE_2);
                    if (DateTimeUtils.nowAfter(endDate)) {
                        mTvTimeResult.setText("Đã hết hạn");
                        mTvTimeResult.setTextColor(getResources().getColor(R.color.red));
                        mTvTimeResult.setText("Đã hết hạn");
                        mBtnPickFile.setVisibility(View.GONE);
                    } else {
                        mBtnPickFile.setVisibility(View.VISIBLE);
                        mTvTimeResult.setText("Đang diễn ra");
                        mTvTimeResult.setTextColor(getResources().getColor(R.color.green));
                    }
                    visibleUploadFile(View.VISIBLE);
                    showLoading(false);
                }
                FilePicker.setFile(null);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FilePicker.callBackPicker(requestCode, resultCode, data);
        uploadFile();
    }

    private void uploadFile() {

        try {
            if (FilePicker.getFile().length() < 5000000) {
                showLoading(true);
                String data = FilePicker.convertBase64(FilePicker.getFile());
                String name = FilePicker.getName();
                FileAttach fileAttach = new FileAttach(
                        name, EnviromentSingleton.getEnviromentSingleton().getIdDocumentType(), EnviromentSingleton.getEnviromentSingleton().getIdSubject(), EnviromentSingleton.getEnviromentSingleton().getIdClass(), data);
                Client.getInstance().createFileAttach(fileAttach).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        BaseResponse baseResponse = response.body();
                        showLoading(false);
                        if (baseResponse != null && baseResponse.getError().getCode() == 0) {
                            fetchData();
                        }

                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        showLoading(false);
                    }
                });
            } else {
                Utils.showToast(DetailAssignmentActivity.this, "Vui lòng chọn file có kích thước nhỏ hơn 5 MB");
            }
        }catch (Exception e){
            e.printStackTrace();

        }


    }


    private void visibleUploadFile(int visible) {
        mCvSchedule.setVisibility(visible);
        mCvSubmisstion.setVisibility(visible);
        mTvSchedule.setVisibility(visible);
        mTvSubmission.setVisibility(visible);
    }
}