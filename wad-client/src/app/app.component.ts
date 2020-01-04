import {AfterViewInit, Component, ElementRef, ViewChild} from '@angular/core';
import {loadModules} from 'esri-loader';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
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
      url: "https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trailheads_Styled/FeatureServer/0",
    });

    let view = new this.MapView({
      container: this.mapViewEl.nativeElement,
      map: map,
      center: [-118.80500, 34.02700], // longitude, latitude
      zoom: 10
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
                title: feature.attributes.TRL_NAME,
                content: 'This a ' + feature.attributes.PARK_NAME + ' trail located in ' + feature.attributes.CITY_JUR + '.',
                location: feature.geometry
              });
            }
          });
        });
      });
    });

    view.when(() => {
      let layer = map.layers.getItemAt(0);

      let legend = new this.Legend({
        view: view,
        layerInfos: [
          {
            layer: layer,
            title: "test"
          }
        ]
      });

      view.ui.add(legend, "bottom-right");
    });

    view.on("click", (event) => {
      this.queryFeatureLayerView(map, featureLayer, event.mapPoint, 1500, "intersects", null, view);
    });

    view.ui.add(this.createBaseMapGallery(view), "top-right");

    map.add(this.createPopupTrailHeads());
    map.add(this.createTrailsFeatureLayer(),0);
    map.add(this.graphicsLayer);
    map.add(this.createParksAndOpenSpaceLayer(),0);
    map.add(this.createTrailsLayer(), 0);
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

  private createTrailsFeatureLayer() {
    let popupTrails = {
      title: 'Trail Information',
      content: () => 'This is {TRL_NAME} with {ELEV_GAIN} ft of climbing.'
    };

    return new this.FeatureLayer({
      url: 'https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trails_Styled/FeatureServer/0',
      outFields: ['TRL_NAME', 'ELEV_GAIN'],
      popupTemplate: popupTrails
    });
  }

  private createPopupTrailHeads() {
    let popupTrailheads = {
      'title': 'Trailhead',
      'content': '<b>Trail:</b> {TRL_NAME}<br><b>City:</b> {CITY_JUR}<br><b>Cross Street:</b> {X_STREET}<br><b>Parking:</b> {PARKING}<br><b>Elevation:</b> {ELEV_FT} ft'
    };

    return new this.FeatureLayer({
      url: 'https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trailheads_Styled/FeatureServer/0',
      outFields: ['TRL_NAME', 'CITY_JUR', 'X_STREET', 'PARKING', 'ELEV_FT'],
      popupTemplate: popupTrailheads
    });
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
        },
        popupTemplate: {
          title: '{TRL_NAME}',
          content: 'This a {PARK_NAME} trail located in {CITY_JUR}.'
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
}
