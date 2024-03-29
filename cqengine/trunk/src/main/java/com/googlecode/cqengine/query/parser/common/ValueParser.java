/**
 * Copyright 2012-2015 Niall Gallagher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.cqengine.query.parser.common;

/**
 * @author Niall Gallagher
 */
public abstract class ValueParser<A> {

    protected final Class<A> valueType;

    public ValueParser(Class<A> valueType) {
        this.valueType = valueType;
    }

    public Class<A> getValueType() {
        return valueType;
    }

    public A validatedParse(String stringValue) {
        try {
            return parse(stringValue);
        }
        catch (Exception e) {
            throw new InvalidQueryException("Failed to parse as type " + this.getValueType().getSimpleName() + ": " + stringValue);
        }
    }

    protected abstract A parse(String stringValue);
}
