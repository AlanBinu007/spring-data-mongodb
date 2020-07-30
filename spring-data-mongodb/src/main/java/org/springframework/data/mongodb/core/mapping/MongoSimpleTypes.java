/*
 * Copyright 2011-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.mongodb.core.mapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.bson.*;
import org.bson.types.Binary;
import org.bson.types.Code;
import org.bson.types.CodeWScope;
import org.bson.types.CodeWithScope;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.bson.types.Symbol;
import org.springframework.data.mapping.model.SimpleTypeHolder;

import com.mongodb.DBRef;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.GeometryCollection;
import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.MultiLineString;
import com.mongodb.client.model.geojson.MultiPoint;
import com.mongodb.client.model.geojson.MultiPolygon;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Polygon;

/**
 * Simple constant holder for a {@link SimpleTypeHolder} enriched with Mongo specific simple types.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public abstract class MongoSimpleTypes {

	public static final Set<Class<?>> AUTOGENERATED_ID_TYPES;

	static {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(ObjectId.class);
		classes.add(String.class);
		classes.add(BigInteger.class);
		AUTOGENERATED_ID_TYPES = Collections.unmodifiableSet(classes);

		Set<Class<?>> simpleTypes = new HashSet<>();
		simpleTypes.add(Binary.class);
		simpleTypes.add(DBRef.class);
		simpleTypes.add(Decimal128.class);
		simpleTypes.add(org.bson.Document.class);
		simpleTypes.add(Code.class);
		simpleTypes.add(CodeWScope.class);
		simpleTypes.add(CodeWithScope.class);
		simpleTypes.add(ObjectId.class);
		simpleTypes.add(Pattern.class);
		simpleTypes.add(Symbol.class);
		simpleTypes.add(UUID.class);

		simpleTypes.add(BsonBinary.class);
		simpleTypes.add(BsonBoolean.class);
		simpleTypes.add(BsonDateTime.class);
		simpleTypes.add(BsonDbPointer.class);
		simpleTypes.add(BsonDecimal128.class);
		simpleTypes.add(BsonDocument.class);
		simpleTypes.add(BsonDouble.class);
		simpleTypes.add(BsonInt32.class);
		simpleTypes.add(BsonInt64.class);
		simpleTypes.add(BsonJavaScript.class);
		simpleTypes.add(BsonJavaScriptWithScope.class);
		simpleTypes.add(BsonObjectId.class);
		simpleTypes.add(BsonRegularExpression.class);
		simpleTypes.add(BsonString.class);
		simpleTypes.add(BsonTimestamp.class);

		simpleTypes.add(Geometry.class);
		simpleTypes.add(GeometryCollection.class);
		simpleTypes.add(LineString.class);
		simpleTypes.add(MultiLineString.class);
		simpleTypes.add(MultiPoint.class);
		simpleTypes.add(MultiPolygon.class);
		simpleTypes.add(Point.class);
		simpleTypes.add(Polygon.class);

		MONGO_SIMPLE_TYPES = Collections.unmodifiableSet(simpleTypes);
	}

	private static final Set<Class<?>> MONGO_SIMPLE_TYPES;

	public static final SimpleTypeHolder HOLDER = new SimpleTypeHolder(MONGO_SIMPLE_TYPES, true) {

		@Override
		public boolean isSimpleType(Class<?> type) {

			if (type.isEnum()) {
				return true;
			}

			if (type.getName().startsWith("java.time")) {
				return false;
			}

			return super.isSimpleType(type);
		}
	};

	private MongoSimpleTypes() {}
}
