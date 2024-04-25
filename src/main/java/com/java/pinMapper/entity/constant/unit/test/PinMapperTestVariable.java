package com.java.pinMapper.entity.constant.unit.test;

import com.java.pinMapper.entity.dao.RouteInfo;
import com.java.pinMapper.entity.pojo.DirectionSteps;
import com.java.pinMapper.entity.pojo.RouteResponse;
import com.java.pinMapper.entity.pojo.outbound.Distance;
import com.java.pinMapper.entity.pojo.outbound.Duration;
import com.java.pinMapper.entity.pojo.outbound.GoogleRouteResponse;
import com.java.pinMapper.entity.pojo.outbound.Leg;
import com.java.pinMapper.entity.pojo.outbound.Location;
import com.java.pinMapper.entity.pojo.outbound.OverviewPolyline;
import com.java.pinMapper.entity.pojo.outbound.Route;
import com.java.pinMapper.entity.pojo.outbound.Step;
import java.util.Collections;
import java.util.List;

public class PinMapperTestVariable {

  public static final Integer ORIGIN = 110023;
  public static final String ORIGIN_STRING = "110023";
  public static final String DESTINATION_STRING = "110025";
  public static final Integer DESTINATION = 110025;
  public static final String ORIGIN_PARAM="origin";
  public static final String DESTINATION_PARAM="destination";
  public static final String DURATION = "10 mins";
  public static final String DISTANCE = "10 km";
  public static final String DIRECTION_INFO="Head <b>northwest</b> on <b>Feroze Gandhi Rd</b> toward <b>Shraddha Nand Gali</b>";
  public static final String DIRECTION_DURATION="3 mins";
  public static final String DIRECTION_DISTANCE="0.6 km";
  public static final String POINTS = "iwjmDggmvMiD~KK^[n@yAzEs@xCiArDOb@`Ab@`Br@lEjBt@d@N@PDzAf@tAZrDb@|ANU`AcEi@_AGo@O{Ag@aAi@qJ}EUQc@SkCgAqBaAyDsBoHkDuAk@e@Iq@EiB@kFDcE@kGB}BAiBM{@Uc@OoAk@cEuBeCsA{BgAcBeAc@Mg@GiACcFCiCGwBDs@Cg@?m@Dq@PeCnAmAl@c@Rw@Tk@H_ADeDCsHGu@CsBQyFq@cCUcBK{BUaCUeAMk@EeABy@FkDLaFL}Vr@qFLoFTqBCoL\\\\wB@oGGuBKcE?U?gCGgGOCLEDG@uCHiBD]OKKIOCW@k@HUJONIPERAp@Nt@RPH`@Cz@OdDkBhA_@~@MlBI`@OHE@_@NaFB}C?sBLuGHm@DiBDe@Lo@Rs@Vc@BWB]@G@O@YAUAIWs@GOw@iD_@_B]iBMWOMWUIQKUSI}Cn@sBf@qCn@iDj@iAX}HdB}LlCoCl@kDbAkGrAaI~Am@FeAFuADoC@yC?_DDcG@qFBu@LoAC}BDa@BKSMGo@GyAOkCGgD?]CYEUO[_@Qi@G}@BkI?{M@{CNeCr@}DV{AHm@?m@KgAK[MSc@c@c@WaBc@eKeCiEeAeFoAcAMmAEwAF_ANqAf@iDjBcNdI_B|@uHnEoDvBaB|@uL~FgBz@{BtA}A~@aFrCuA~@aCdAaBj@kFdBaBRoEDq@Hw@Tc@Zk@t@{AdCk@hAQr@Et@Bh@ZnC\\\\fCJh@Bd@E|@Mf@CLYTi@n@iAbBkAnBmBlDiCrEc@hAUr@UUAg@Oy@[{@a@y@c@u@a@{@Ku@AgARkCNgAVw@Bi@Jk@Vs@HqAFg@FOFIB?Ke@Oe@[gAkA}D{BgHmIqXW_AI@S@i@KeCOm@KUKSKEGMYOk@sAwGc@}CQwAWqAg@gCg@}CaDoSgAsHOa@QW]SWEMCEVCJGFg@d@i@x@cBhB{@v@Y\\\\OFa@j@S^[|@GZWlBi@bGqAQoEw@iDg@yF{@a@IB]";

  public static final DirectionSteps DIRECTION_STEPS=DirectionSteps
      .builder()
      .directionInfo(DIRECTION_INFO)
      .distance(DIRECTION_DISTANCE)
      .duration(DIRECTION_DURATION)
      .build();

  public static final List<DirectionSteps> STEPS= Collections.singletonList(DIRECTION_STEPS);

  public static final OverviewPolyline OVERVIEW_POLYLINE = OverviewPolyline.builder()
      .points(POINTS)
      .build();
  public static final Location ORIGIN_LOCATION = Location.builder()
      .lat(1111d)
      .lng(1111d).build();
  public static final Location DESTINATION_LOCATION = Location.builder()
      .lat(1111d)
      .lng(1111d).build();

  public static final RouteResponse ROUTE_RESPONSE = RouteResponse.builder()
      .originPincode(ORIGIN_STRING)
      .destinationPincode(DESTINATION_STRING)
      .originLocation(ORIGIN_LOCATION)
      .destinationLocation(DESTINATION_LOCATION)
      .duration(DURATION)
      .distance(DISTANCE)
      .polygonInfo(OVERVIEW_POLYLINE)
      .steps(STEPS)
      .build();
  public static final List<Route> ROUTE =Collections.singletonList(Route.builder().legs(Collections.singletonList(
      Leg.builder().distance(Distance.builder().text(DISTANCE).build()).duration(Duration.builder().text(DURATION).build()).end_location(DESTINATION_LOCATION).start_location(ORIGIN_LOCATION)
          .steps(Collections.singletonList(Step.builder().distance(Distance.builder().text(DIRECTION_DISTANCE).build()).duration(Duration.builder().text(DIRECTION_DURATION).build())
              .html_instructions(DIRECTION_INFO).build())).build()))
      .overview_polyline(OVERVIEW_POLYLINE)
      .build());

  public static final GoogleRouteResponse GOOGLE_ROUTE_RESPONSE=GoogleRouteResponse.builder()
      .status("OK")
      .routes(ROUTE)
      .build();

  public static final RouteInfo ROUTE_INFO=RouteInfo.builder()
      .destinationLocation(DESTINATION_LOCATION)
      .originPincode(ORIGIN)
      .originLocation(ORIGIN_LOCATION)
      .polygonInfo(OVERVIEW_POLYLINE)
      .destinationPincode(DESTINATION)
      .distance(DISTANCE)
      .duration(DURATION)
      .steps(STEPS)
      .build();


}
