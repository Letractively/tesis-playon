package tesis.playon.mobile.provider;

import tesis.playon.mobile.Const.DbValues;
import tesis.playon.mobile.provider.ParkingDatabase.Tables;

import android.net.Uri;
import android.provider.BaseColumns;

public class ParkingContract {

    @SuppressWarnings("unused")
    private static final String TAG = "ParkingContract";

    public static final long UPDATED_NEVER = -2;

    public static final long UPDATED_UNKNOWN = -1;

    public static interface SyncColumns {
	final String UPDATED = "updated";
    }

    public static interface PostsColumns {
	final String ID_POST = "id_post";
	final String LNG = "lng";
	final String LAT = "lat";
	final String GEO_DISTANCE = "post_geo_distance";
    }

    public static interface PanelsColumns {
	final String ID_PANEL = "id_panel";
	final String ID_POST = "id_post";
	final String ID_PANEL_CODE = "id_panel_code";
	final String ARROW = "arrow";
    }

    public static interface PanelsCodesColumns {
	final String CODE = "code";
	final String DESCRIPTION = "description";
	final String TYPE_DESC = "type_desc";
    }

    public static interface PanelsCodesRulesColumns {
	final String ID_PANEL_CODE = "id_panel_code";
	final String MINUTES_DURATION = "minutes_duration";
	final String HOUR_START = "hour_start";
	final String HOUR_END = "hour_end";
	final String HOUR_DURATION = "hour_duration";
	final String DAY_START = "day_start";
	final String DAY_END = "day_end";
    }

    public static interface FavoritesColumns {
	final String ID_POST = "id_post";
    }

    public static final String CONTENT_AUTHORITY = "ca.mudar.parkcatcher";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_POSTS = "posts";
    private static final String PATH_PANELS = "panels";
    private static final String PATH_PANELS_CODES = "panels_codes";
    private static final String PATH_PANELS_CODES_RULES = "panels_codes_rules";
    private static final String PATH_FAVORITES = "favorites";
    private static final String PATH_POSTS_ALLOWED = "allowed";
    private static final String PATH_POSTS_FORBIDDEN = "fobidden";
    private static final String PATH_POSTS_STARRED = "starred";

    public static class Posts implements PostsColumns, SyncColumns, BaseColumns {
	public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POSTS).build();

	public static final Uri CONTENT_ALLOWED_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POSTS)
		.appendPath(PATH_POSTS_ALLOWED).build();
	public static final Uri CONTENT_FORBIDDEN_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POSTS)
		.appendPath(PATH_POSTS_FORBIDDEN).build();
	public static final Uri CONTENT_STARRED_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POSTS)
		.appendPath(PATH_POSTS_STARRED).build();

	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.parkcatcher.post";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.parkcatcher.post";

	public static final String DEFAULT_SORT = BaseColumns._ID + " ASC ";
	public static final String DISTANCE_SORT = PostsColumns.GEO_DISTANCE + " ASC ";

	public static Uri buildPostUri(String id) {
	    return CONTENT_URI.buildUpon().appendPath(id).build();
	}

	public static String getPostId(Uri uri) {
	    return uri.getPathSegments().get(1);
	}
    }

    public static class Panels implements PanelsColumns, SyncColumns, BaseColumns {
	public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PANELS).build();

	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.parkcatcher.panel";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.parkcatcher.panel";

	public static final String DEFAULT_SORT = BaseColumns._ID + " ASC ";

	public static Uri buildPanelUri(String id) {
	    return CONTENT_URI.buildUpon().appendPath(id).build();
	}

	public static String getPanelId(Uri uri) {
	    return uri.getPathSegments().get(1);
	}
    }

    public static class PanelsCodes implements PanelsCodesColumns, SyncColumns, BaseColumns {

	public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PANELS_CODES).build();

	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.parkcatcher.code";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.parkcatcher.code";

	public static final String CONCAT_ID_PANEL_CODE = "GROUP_CONCAT(" + Tables.PANELS_CODES + "." + BaseColumns._ID
		+ ", '" + DbValues.CONCAT_SEPARATOR + "') AS " + PanelsColumns.ID_PANEL_CODE;
	public static final String CONCAT_DESCRIPTION = "GROUP_CONCAT(" + PanelsCodesColumns.DESCRIPTION + ", '"
		+ DbValues.CONCAT_SEPARATOR + "') AS " + PanelsCodesColumns.DESCRIPTION;
	public static final String CONCAT_TYPE_DESC = "GROUP_CONCAT(" + PanelsCodesColumns.TYPE_DESC + ", '"
		+ DbValues.CONCAT_SEPARATOR + "') AS " + PanelsCodesColumns.TYPE_DESC;

	public static final String DEFAULT_SORT = BaseColumns._ID + " ASC ";

	public static Uri buildPanelCodeUri(String id) {
	    return CONTENT_URI.buildUpon().appendPath(id).build();
	}

	public static String getPanelCodeId(Uri uri) {
	    return uri.getPathSegments().get(1);
	}
    }

    public static class PanelsCodesRules implements PanelsCodesRulesColumns, SyncColumns, BaseColumns {

	public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PANELS_CODES_RULES).build();

	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.parkcatcher.rule";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.parkcatcher.rule";

	public static final String DEFAULT_SORT = BaseColumns._ID + " ASC ";

	public static Uri buildPanelCodeRuleUri(String id) {
	    return CONTENT_URI.buildUpon().appendPath(id).build();
	}

	public static String getPanelCodeRuleId(Uri uri) {
	    return uri.getPathSegments().get(1);
	}
    }

    public static class Favorites implements FavoritesColumns, SyncColumns, BaseColumns {
	public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITES).build();

	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.parkcatcher.favorite";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.parkcatcher.favorite";

	public static final String DEFAULT_SORT = PostsColumns.GEO_DISTANCE + " ASC ";

	public static Uri buildFavoriteUri(String id) {
	    return CONTENT_URI.buildUpon().appendPath(id).build();
	}

	public static String getFavoriteId(Uri uri) {
	    return uri.getPathSegments().get(1);
	}
    }

    private ParkingContract() {
    }
}
