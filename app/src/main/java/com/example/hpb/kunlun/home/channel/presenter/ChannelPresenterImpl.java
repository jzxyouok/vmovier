package com.example.hpb.kunlun.home.channel.presenter;

import com.example.hpb.kunlun.data.HttpResultFunc;
import com.example.hpb.kunlun.data.Repository;
import com.example.hpb.kunlun.data.RxHelp;
import com.example.hpb.kunlun.home.channel.view.IChannelView;
import com.example.hpb.kunlun.home.model.Cate;
import java.util.List;
import rx.Observable;

/**
 * Created by 0000- on 2016/6/9.
 */
public class ChannelPresenterImpl implements IchannelPresenter {

  IChannelView channelView;

  public ChannelPresenterImpl(IChannelView channelView) {
    this.channelView = channelView;
  }

  @Override public void getAllChannel() {
    Observable<List<Cate>> observable = Repository.getInstance()
        .getVmovierApi()
        .getCateList()
        .map(new HttpResultFunc<List<Cate>>());
    new RxHelp<List<Cate>>().toSubscribe(observable, new RxHelp.OnNext<List<Cate>>() {
      @Override public void onNext(List<Cate> cates) {
        channelView.onLoadChannels(cates);
      }
    });
  }
}
