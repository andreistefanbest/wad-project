import {AfterViewInit, Component, ElementRef, ViewChild, ViewEncapsulation} from '@angular/core';
import {loadModules} from 'esri-loader';
import {DatePipe} from '@angular/common';
import {AlertsComponent} from './alerts/alerts.component';
import {MatDialog} from '@angular/material';

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
  private mapViewEl: ElementRef;

  constructor(public datePipe: DatePipe,
              public dialog: MatDialog) {
  }

  private popupOpenspaces = {
    "title": "{PARK_NAME}",
    "content": [{
      "type": "fields",
      "fieldInfos": [
        {
          "fieldName": "AGNCY_NAME",
          "label": "Agency",
          "isEditable": true,
          "tooltip": "",
          "visible": true,
          "format": null,
          "stringFieldOption": "text-box"
        },
        {
          "fieldName": "TYPE",
          "label": "Type",
          "isEditable": true,
          "tooltip": "",
          "visible": true,
          "format": null,
          "stringFieldOption": "text-box"
        },
        {
          "fieldName": "ACCESS_TYP",
          "label": "Access",
          "isEditable": true,
          "tooltip": "",
          "visible": true,
          "format": null,
          "stringFieldOption": "text-box"
        },
        {
          "fieldName": "GIS_ACRES",
          "label": "Acres",
          "isEditable": true,
          "tooltip": "",
          "visible": true,
          "format": {
            "places": 2,
            "digitSeparator": true
          },
          "stringFieldOption": "text-box"
        }
      ]
    }]
  };
  private graphicsLayer;

  ngAfterViewInit(): void {
    this.initESRI();
  }

  async initESRI() {
    await this.initESRIComponents();

    let map = new this.Map({
      basemap: "streets-navigation-vector"
    });

    let featureLayer = new this.FeatureLayer({
      url: "https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Control_points/FeatureServer/1",
    });

    let view = new this.MapView({
      container: this.mapViewEl.nativeElement,
      map: map,
      center: [26.083373, 44.463458], // longitude, latitude
      zoom: 14
    });

    view.when(() => {
      view.ui.add(new this.LayerList({view}), "bottom-left");
    });

    view.when(() => {
      this.queryFeatureLayerView(map, featureLayer, view.center, 1500, "intersects", null, view);
    });

    view.when(() => {
      view.whenLayerView(featureLayer).then((featureLayerView) => {
        view.on("pointer-move", (event) => {
          view.hitTest(event).then((response) => {
            let feature = response.results.filter((r) => r.graphic.layer === featureLayer)[0].graphic;
            if (!feature) {
              return;
            }

            if (!view.popup.features.length || view.popup.features.length && (view.popup.features[0].attributes.FID !== feature.attributes.FID)) {
              view.popup.open({
                title: feature.attributes.TITLE,
                content: `<STRONG> FLOW: </STRONG>` + feature.attributes.FLOW + `<br>` +
                  `<STRONG> PRESSURE: </STRONG>` + feature.attributes.PRESSURE + `<br>` +
                  `<STRONG> POWER CONSUMPTION: </STRONG>` + feature.attributes.POWER_CONS + `<br>` +
                  `<STRONG> PROTECTION: </STRONG>` + feature.attributes.PROTECTION + `<br>` +
                  `<STRONG> TEMPERATURE: </STRONG>` + feature.attributes.TEMPERATURE + `<br>` +
                  `<span>Raportare Problema</span>`,
                location: feature.geometry
              });
            }
          });
        });
      });
    });

    view.when(() => {
      let legend = new this.Legend({
        view: view,
        layerInfos: [
          {
            layer: map.layers.getItemAt(1),
            title: "Legend"
          }
        ]
      });

      view.ui.add(legend, "bottom-right");
    });

    view.on("click", (event) => {
      this.queryFeatureLayerView(map, featureLayer, event.mapPoint, 1500, "intersects", null, view);
    });

    view.ui.add(this.createBaseMapGallery(view), "top-right");

    map.add(this.createWaterPipesLayer());

    this.addAlertPopupCallback(view);

    map.add(new this.FeatureLayer({
      url: "https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Centre_de_control/FeatureServer/3",
    }));

    map.add(new this.FeatureLayer({
      url: "https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Retea_de_Tevi/FeatureServer/2",
    }));

    map.add(new this.FeatureLayer({
      url: "https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Service/FeatureServer",
    }));
  }

  private createParksAndOpenSpaceLayer() {
    return new this.FeatureLayer({
      url: 'https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Parks_and_Open_Space_Styled/FeatureServer/0',
      outFields: ['TYPE', 'PARK_NAME', 'AGNCY_NAME', 'ACCESS_TYP', 'GIS_ACRES', 'TRLS_MI', 'TOTAL_GOOD', 'TOTAL_FAIR', 'TOTAL_POOR'],
      popupTemplate: this.popupOpenspaces
    });
  }

  private createTrailsLayer() {
    return new this.FeatureLayer({
      url: 'https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trails/FeatureServer/0',
      renderer: {
        type: 'simple',
        symbol: {
          type: 'simple-line',
          color: 'red',
          width: '2px'
        }
      },
      outFields: ['TRL_NAME', 'ELEV_GAIN'],
      popupTemplate: {
        title: '{TRL_NAME}',
        content: 'The trail elevation gain is {ELEV_GAIN} ft.'
      }
    });
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

  private createBaseMapGallery(view) {
    return new this.BasemapGallery({
      view: view,
      source: {
        portal: {
          url: 'https://www.arcgis.com',
          useVectorBasemaps: true
        }
      }
    });
  }

  private addGraphics(graphicsLayer, result, Graphic) {
    graphicsLayer.removeAll();
    result.features.forEach(feature => {
      let g = new Graphic({
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

  private queryFeatureLayerView(map, featureLayer, point, distance, spatialRelationship, sqlExpression, view) {
    if (!map.findLayerById(featureLayer.id)) {
      featureLayer.outFields = ['*'];
      map.add(featureLayer, 0);
    }
    let query = {
      geometry: point,
      distance: distance,
      spatialRelationship: spatialRelationship,
      outFields: ['*'],
      returnGeometry: true,
      where: sqlExpression
    };
    view.whenLayerView(featureLayer).then((featureLayerView) => {
      if (featureLayerView.updating) {
        let handle = featureLayerView.watch('updating', (isUpdating) => {
          if (!isUpdating) {
            featureLayerView.queryFeatures(query).then((result) => {
              this.addGraphics(this.graphicsLayer, result, this.Graphic);
            });
            handle.remove();
          }
        });
      } else {
        featureLayerView.queryFeatures(query).then((result) => {
          this.addGraphics(this.graphicsLayer, result, this.Graphic);
        });
      }
    });
  }

  private createWaterPipesLayer() {
    return new this.FeatureLayer({
      url: "https://services7.arcgis.com/XJBrmLgIMyI2zI18/arcgis/rest/services/Pipe_System/FeatureServer/2",
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
