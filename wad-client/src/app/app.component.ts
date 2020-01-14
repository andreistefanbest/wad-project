import {AfterViewInit, Component, ElementRef, ViewChild, ViewEncapsulation} from '@angular/core';
import {loadModules} from 'esri-loader';
import {AlertsComponent} from './alerts/alerts.component';
import {MatDialog} from '@angular/material';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements AfterViewInit {
  public Map;
  public MapView;
  public BasemapToggle;
  public BasemapGallery;
  public FeatureLayer;
  public GraphicsLayer;
  public Graphic;
  public Legend;
  public LayerList;

  @ViewChild("viewDiv")
  private mapContainer: ElementRef;

  constructor(public dialog: MatDialog,
              public datePipe: DatePipe) {
  }

  private graphicsLayer;

  ngAfterViewInit(): void {
    this.initESRI();
  }

  async initESRI() {
    await this.initESRIComponents();

    let map = new this.Map({
      basemap: "streets-navigation-vector"
    });

    let view = new this.MapView({
      container: this.mapContainer.nativeElement,
      map: map,
      center: [26.083373, 44.463458], // longitude, latitude
      zoom: 14
    });

    this.addLayerListWidget(view);
    this.addBaseMapWidget(view);
    this.addLegend(view, map);
    this.addAlertPopupCallback(view);

    this.addControlPointsLayer(view, map);
    this.addServiceLayer(view, map);
    this.addControlAreasLayer(map);
    this.addReteaTeviLayer(map);
  }

  private addServiceLayer(view, map) {
    let featureLayer = new this.FeatureLayer({
      url: 'https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Service_2/FeatureServer',
    });

    view.when(() => {
      this.queryFeatureLayerView(map, featureLayer, view.center, view);
    });

    view.when(() => {
      view.whenLayerView(featureLayer).then(() => {
        view.on('pointer-move', (event) => {
          view.hitTest(event).then((response) => {
            let feature = response.results.filter((r) => r.graphic.layer === featureLayer)[0].graphic;
            if (!feature) {
              return;
            }

            let popup = view.popup;
            if (!popup.features.length || popup.features.length && (popup.features[0].attributes.FID !== feature.attributes.FID)) {
              popup.open(this.alertePopupTemplate(feature));
            }
          });
        });
      });
    });
  }

  private addReteaTeviLayer(map) {
    map.add(new this.FeatureLayer({
      url: 'https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Retea_de_Tevi/FeatureServer/2',
    }));
  }

  private addControlAreasLayer(map) {
    map.add(new this.FeatureLayer({
      url: 'https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Centre_de_control/FeatureServer/3',
    }));
  }

  private addBaseMapWidget(view) {
    view.ui.add(new this.BasemapGallery({
      view: view,
      source: {
        portal: {
          url: 'https://www.arcgis.com',
          useVectorBasemaps: true
        }
      }
    }), 'top-right');
  }

  private addLayerListWidget(view) {
    view.when(() => {
      view.ui.add(new this.LayerList({view}), 'bottom-left');
    });
  }

  private addLegend(view, map) {
    view.when(() => {
      let legend = new this.Legend({
        view: view,
        layerInfos: [
          {
            layer: map.layers.getItemAt(0),
            title: 'Legend'
          }
        ]
      });

      view.ui.add(legend, 'bottom-right');
    });
  }

  private addControlPointsLayer(view, map) {
    let featureLayer = new this.FeatureLayer({
      url: 'https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Control_points/FeatureServer/1',
    });

    view.when(() => {
      this.queryFeatureLayerView(map, featureLayer, view.center, view);
    });

    view.when(() => {
      view.whenLayerView(featureLayer).then(() => {
        view.on('pointer-move', (event) => {
          view.hitTest(event).then((response) => {
            let feature = response.results.filter((r) => r.graphic.layer === featureLayer)[0].graphic;
            if (!feature) {
              return;
            }

            let popup = view.popup;
            if (!popup.features.length || popup.features.length && (popup.features[0].attributes.FID !== feature.attributes.FID)) {
              popup.open(this.punctControlPopupTemplate(feature));
            }
          });
        });
      });
    });
  }

  private punctControlPopupTemplate(feature) {
    return {
      title: feature.attributes.TITLE,
      content: `<STRONG> FLOW: </STRONG>` + feature.attributes.FLOW + `<br>` +
        `<STRONG> PRESSURE: </STRONG>` + feature.attributes.PRESSURE + `<br>` +
        `<STRONG> POWER CONSUMPTION: </STRONG>` + feature.attributes.POWER_CONS + `<br>` +
        `<STRONG> PROTECTION: </STRONG>` + feature.attributes.PROTECTION + `<br>` +
        `<STRONG> TEMPERATURE: </STRONG>` + feature.attributes.TEMPERATURE + `<br>` +
        `<span>Raportare Problema</span>`,
      location: feature.geometry
    };
  }

  private alertePopupTemplate(feature) {
    return {
      title: 'Problema raportata in punctul ' + feature.attributes.LONGITUDE + ', ' + feature.attributes.LATITUDE,
      content: `<STRONG> Nume Reclamant: </STRONG>` + feature.attributes.NAME + `<br>` +
        `<STRONG> Nr. Telefon: </STRONG>` + feature.attributes.PHONE + `<br>` +
        `<br><br>` +
        `<STRONG> Data Raportare: </STRONG>` + this.datePipe.transform(feature.attributes.ENTRY_DATE, 'medium').toString() + `<br>` +
        `<STRONG> Status: </STRONG>` + feature.attributes.STATUS + `<br>`,
      location: feature.geometry
    };
  }

  private async initESRIComponents() {
    const [
      Map,
      MapView,
      BasemapToggle,
      BasemapGallery,
      FeatureLayer,
      GraphicsLayer,
      Graphic,
      Legend,
      LayerList] = await loadModules(['esri/Map',
      'esri/views/MapView',
      'esri/widgets/BasemapToggle',
      'esri/widgets/BasemapGallery',
      'esri/layers/FeatureLayer',
      'esri/layers/GraphicsLayer',
      'esri/Graphic',
      'esri/widgets/Legend',
      'esri/widgets/LayerList']);

    this.Map = Map;
    this.MapView = MapView;
    this.BasemapToggle = BasemapToggle;
    this.BasemapGallery = BasemapGallery;
    this.FeatureLayer = FeatureLayer;
    this.GraphicsLayer = GraphicsLayer;
    this.Graphic = Graphic;
    this.Legend = Legend;
    this.LayerList = LayerList;

    this.graphicsLayer = new this.GraphicsLayer();
  }
  private addGraphics(graphicsLayer, result) {
    graphicsLayer.removeAll();
    result.features.forEach(feature => {
      let g = new this.Graphic({
        geometry: feature.geometry,
        attributes: feature.attributes,
        symbol: {
          type: 'simple-marker',
          color: [0, 0, 0],
          outline: {
            width: 2,
            color: [0, 255, 255],
          },
          size: '20px'
        }
      });
      graphicsLayer.add(g);
    });
  }

  private queryFeatureLayerView(map, featureLayer, point, view) {
    if (!map.findLayerById(featureLayer.id)) {
      featureLayer.outFields = ['*'];
      map.add(featureLayer, 0);
    }
    let query = {
      geometry: point,
      distance: 1500,
      spatialRelationship: 'íntersects',
      outFields: ['*'],
      returnGeometry: true,
      where: null
    };
    view.whenLayerView(featureLayer).then((featureLayerView) => {
      if (featureLayerView.updating) {
        let handle = featureLayerView.watch('updating', (isUpdating) => {
          if (!isUpdating) {
            featureLayerView.queryFeatures(query).then((result) => {
              this.addGraphics(this.graphicsLayer, result);
            });
            handle.remove();
          }
        });
      } else {
        featureLayerView.queryFeatures(query).then((result) => {
          this.addGraphics(this.graphicsLayer, result);
        });
      }
    });
  }
  private addAlertPopupCallback(map: any) {
    map.on('click', (event) => {
      this.dialog.open(AlertsComponent, {
        width: (window.innerWidth < 760 ? '100%' : '70%'),
        data: { event }
      });
    });
  }
}
