/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class FriendlyMessage {

    public static final String TEXT = "TEXT";
    public static final String NAME = "NAME";
    public static final String PHOTO = "PHOTO";
    private String text;
    private String name;
    private String photoUrl;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public FriendlyMessage(Bundle b) {
        if (b != null) {
            this.text = b.getString(TEXT);
            this.name = b.getString(NAME);
            this.photoUrl = b.getString(PHOTO);
        }
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(TEXT, this.text);
        b.putString(NAME, this.name);
        b.putString(PHOTO, this.photoUrl);

        return b;
    }

        public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
