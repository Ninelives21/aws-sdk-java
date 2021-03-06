/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.dynamodbv2.AmazonDynamoDB#query(QueryRequest) Query operation}.
 * <p>
 * A <i>Query</i> operation uses the primary key of a table or a
 * secondary index to directly access items from that table or index.
 * </p>
 * <p>
 * Use the <i>KeyConditionExpression</i> parameter to provide a specific
 * hash key value. The <i>Query</i> operation will return all of the
 * items from the table or index with that hash key value. You can
 * optionally narrow the scope of the <i>Query</i> operation by
 * specifying a range key value and a comparison operator in
 * <i>KeyConditionExpression</i> . You can use the
 * <i>ScanIndexForward</i> parameter to get results in forward or reverse
 * order, by range key or by index key.
 * </p>
 * <p>
 * Queries that do not return results consume the minimum number of read
 * capacity units for that type of read operation.
 * </p>
 * <p>
 * If the total number of items meeting the query criteria exceeds the
 * result set size limit of 1 MB, the query stops and results are
 * returned to the user with the <i>LastEvaluatedKey</i> element to
 * continue the query in a subsequent operation. Unlike a <i>Scan</i>
 * operation, a <i>Query</i> operation never returns both an empty result
 * set and a <i>LastEvaluatedKey</i> value. <i>LastEvaluatedKey</i> is
 * only provided if the results exceed 1 MB, or if you have used the
 * <i>Limit</i> parameter.
 * </p>
 * <p>
 * You can query a table, a local secondary index, or a global secondary
 * index. For a query on a table or on a local secondary index, you can
 * set the <i>ConsistentRead</i> parameter to <code>true</code> and
 * obtain a strongly consistent result. Global secondary indexes support
 * eventually consistent reads only, so do not specify
 * <i>ConsistentRead</i> when querying a global secondary index.
 * </p>
 *
 * @see com.amazonaws.services.dynamodbv2.AmazonDynamoDB#query(QueryRequest)
 */
public class QueryRequest extends AmazonWebServiceRequest implements Serializable, Cloneable {

    /**
     * The name of the table containing the requested items.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     */
    private String tableName;

    /**
     * The name of an index to query. This index can be any local secondary
     * index or global secondary index on the table. Note that if you use the
     * <i>IndexName</i> parameter, you must also provide <i>TableName.</i>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     */
    private String indexName;

    /**
     * The attributes to be returned in the result. You can retrieve all item
     * attributes, specific item attributes, the count of matching items, or
     * in the case of an index, some or all of the attributes projected into
     * the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     * the item attributes from the specified table or index. If you query a
     * local secondary index, then for each matching item in the index
     * DynamoDB will fetch the entire item from the parent table. If the
     * index is configured to project all item attributes, then all of the
     * data can be obtained from the local secondary index, and no fetching
     * is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     * Allowed only when querying an index. Retrieves all attributes that
     * have been projected into the index. If the index is configured to
     * project all attributes, this return value is equivalent to specifying
     * <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     * Returns the number of matching items, rather than the matching items
     * themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     * only the attributes listed in <i>AttributesToGet</i>. This return
     * value is equivalent to specifying <i>AttributesToGet</i> without
     * specifying any value for <i>Select</i>. <p>If you query a local
     * secondary index and request only attributes that are projected into
     * that index, the operation will read only the index and not the table.
     * If any of the requested attributes are not projected into the local
     * secondary index, DynamoDB will fetch each of these attributes from the
     * parent table. This extra fetching incurs additional throughput cost
     * and latency. <p>If you query a global secondary index, you can only
     * request attributes that are projected into the index. Global secondary
     * index queries cannot fetch attributes from the parent table. </li>
     * </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     * specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     * accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     * accessing an index. You cannot use both <i>Select</i> and
     * <i>AttributesToGet</i> together in a single request, unless the value
     * for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     * equivalent to specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     * parameter, then the value for <i>Select</i> can only be
     * <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     * will return an error.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ALL_ATTRIBUTES, ALL_PROJECTED_ATTRIBUTES, SPECIFIC_ATTRIBUTES, COUNT
     */
    private String select;

    /**
     * <important><p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>ProjectionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. <p>This parameter allows you to retrieve attributes of type
     * List or Map; however, it cannot retrieve individual elements within a
     * List or a Map.</important> <p>The names of one or more attributes to
     * retrieve. If no attribute names are provided, then all attributes will
     * be returned. If any of the requested attributes are not found, they
     * will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     * no effect on provisioned throughput consumption. DynamoDB determines
     * capacity units consumed based on item size, not on the amount of data
     * that is returned to an application. <p>You cannot use both
     * <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     * request, <i>unless</i> the value for <i>Select</i> is
     * <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     * specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <p>If you query a local secondary index and request
     * only attributes that are projected into that index, the operation will
     * read only the index and not the table. If any of the requested
     * attributes are not projected into the local secondary index, DynamoDB
     * will fetch each of these attributes from the parent table. This extra
     * fetching incurs additional throughput cost and latency. <p>If you
     * query a global secondary index, you can only request attributes that
     * are projected into the index. Global secondary index queries cannot
     * fetch attributes from the parent table.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     */
    private com.amazonaws.internal.ListWithAutoConstructFlag<String> attributesToGet;

    /**
     * The maximum number of items to evaluate (not necessarily the number of
     * matching items). If DynamoDB processes the number of items up to the
     * limit while processing the results, it stops the operation and returns
     * the matching values up to that point, and a key in
     * <i>LastEvaluatedKey</i> to apply in a subsequent operation, so that
     * you can pick up where you left off. Also, if the processed data set
     * size exceeds 1 MB before DynamoDB reaches this limit, it stops the
     * operation and returns the matching values up to the limit, and a key
     * in <i>LastEvaluatedKey</i> to apply in a subsequent operation to
     * continue the operation. For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html">Query
     * and Scan</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - <br/>
     */
    private Integer limit;

    /**
     * A value that if set to <code>true</code>, then the operation uses
     * strongly consistent reads; otherwise, eventually consistent reads are
     * used. <p>Strongly consistent reads are not supported on global
     * secondary indexes. If you query a global secondary index with
     * <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     * error message.
     */
    private Boolean consistentRead;

    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>KeyConditionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>The selection criteria for the query. For a
     * query on a table, you can have conditions only on the table primary
     * key attributes. You must provide the hash key attribute name and value
     * as an <code>EQ</code> condition. You can optionally provide a second
     * condition, referring to the range key attribute. <note> <p>If you
     * don't provide a range key condition, all of the items that match the
     * hash key will be retrieved. If a <i>FilterExpression</i> or
     * <i>QueryFilter</i> is present, it will be applied after the items are
     * retrieved.</note> <p>For a query on an index, you can have conditions
     * only on the index key attributes. You must provide the index hash
     * attribute name and value as an <code>EQ</code> condition. You can
     * optionally provide a second condition, referring to the index key
     * range attribute. <p>Each <i>KeyConditions</i> element consists of an
     * attribute name to compare, along with the following: <ul> <li>
     * <p><i>AttributeValueList</i> - One or more values to evaluate against
     * the supplied attribute. The number of values in the list depends on
     * the <i>ComparisonOperator</i> being used. <p>For type Number, value
     * comparisons are numeric. <p>String value comparisons for greater than,
     * equals, or less than are based on ASCII character code values. For
     * example, <code>a</code> is greater than <code>A</code>, and
     * <code>a</code> is greater than <code>B</code>. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. </li> <li>
     * <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     * for example, equals, greater than, less than, and so on. <p>For
     * <i>KeyConditions</i>, only the following comparison operators are
     * supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     * BETWEEN</code> <p>The following are descriptions of these comparison
     * operators. <ul> <li> <p><code>EQ</code> : Equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one specified in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     * <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     * can contain only one <i>AttributeValue</i> element of type String,
     * Number, or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GE</code> : Greater than or equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> element of type String, Number, or Binary (not a
     * set type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     * contain only one <i>AttributeValue</i> element of type String, Number,
     * or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     * a prefix. <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String or Binary (not a Number or a set
     * type). The target attribute of the comparison must be of type String
     * or Binary (not a Number or a set type). <p/> </li> <li>
     * <p><code>BETWEEN</code> : Greater than or equal to the first value,
     * and less than or equal to the second value.
     * <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     * elements of the same type, either String, Number, or Binary (not a set
     * type). A target attribute matches if the target value is greater than,
     * or equal to, the first element and less than, or equal to, the second
     * element. If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not compare to
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     * usage examples of <i>AttributeValueList</i> and
     * <i>ComparisonOperator</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     * Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     * Guide</i>.
     */
    private java.util.Map<String,Condition> keyConditions;

    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A condition that evaluates the query
     * results after the items are read and returns only the desired values.
     * <p>This parameter does not support attributes of type List or Map.
     * <note> <p>A <i>QueryFilter</i> is applied after the items have already
     * been read; the process of filtering does not consume any additional
     * read capacity units.</note> <p>If you provide more than one condition
     * in the <i>QueryFilter</i> map, then by default all of the conditions
     * must evaluate to true. In other words, the conditions are ANDed
     * together. (You can use the <i>ConditionalOperator</i> parameter to OR
     * the conditions instead. If you do this, then at least one of the
     * conditions must evaluate to true, rather than all of them.) <p>Note
     * that <i>QueryFilter</i> does not allow key attributes. You cannot
     * define a filter condition on a hash key or range key. <p>Each
     * <i>QueryFilter</i> element consists of an attribute name to compare,
     * along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     * or more values to evaluate against the supplied attribute. The number
     * of values in the list depends on the operator specified in
     * <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     * numeric. <p>String value comparisons for greater than, equals, or less
     * than are based on ASCII character code values. For example,
     * <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     * greater than <code>B</code>. For a list of code values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For type Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. <p>For information on
     * specifying data types in JSON, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     * Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     * <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     * attributes. For example, equals, greater than, less than, etc. <p>The
     * following comparison operators are available: <p><code>EQ | NE | LE |
     * LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     * | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     * operators, see the <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     * data type. </li> </ul>
     */
    private java.util.Map<String,Condition> queryFilter;

    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A logical operator to apply to the
     * conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     * If all of the conditions evaluate to true, then the entire map
     * evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     * the conditions evaluate to true, then the entire map evaluates to
     * true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     * <code>AND</code> is the default. <p>The operation will succeed only if
     * the entire map evaluates to true. <note><p>This parameter does not
     * support attributes of type List or Map.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>AND, OR
     */
    private String conditionalOperator;

    /**
     * A value that specifies ascending (true) or descending (false)
     * traversal of the index. DynamoDB returns results reflecting the
     * requested order determined by the range key. If the data type is
     * Number, the results are returned in numeric order. For type String,
     * the results are returned in order of ASCII character code values. For
     * type Binary, DynamoDB treats each byte of the binary data as unsigned
     * when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     * specified, the results are returned in ascending order.
     */
    private Boolean scanIndexForward;

    /**
     * The primary key of the first item that this operation will evaluate.
     * Use the value that was returned for <i>LastEvaluatedKey</i> in the
     * previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     * be String, Number or Binary. No set data types are allowed.
     */
    private java.util.Map<String,AttributeValue> exclusiveStartKey;

    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     */
    private String returnConsumedCapacity;

    /**
     * A string that identifies one or more attributes to retrieve from the
     * table. These attributes can include scalars, sets, or elements of a
     * JSON document. The attributes in the expression must be separated by
     * commas. <p>If no attribute names are specified, then all attributes
     * will be returned. If any of the requested attributes are not found,
     * they will not appear in the result. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.AccessingItemAttributes.html">Accessing
     * Item Attributes</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>ProjectionExpression</i> replaces the legacy
     * <i>AttributesToGet</i> parameter.</note>
     */
    private String projectionExpression;

    /**
     * A string that contains conditions that DynamoDB applies after the
     * <i>Query</i> operation, but before the data is returned to you. Items
     * that do not satisfy the <i>FilterExpression</i> criteria are not
     * returned. <note> <p>A <i>FilterExpression</i> is applied after the
     * items have already been read; the process of filtering does not
     * consume any additional read capacity units. </note> <p>For more
     * information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html#FilteringResults">Filter
     * Expressions</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>FilterExpression</i> replaces the legacy
     * <i>QueryFilter</i> and <i>ConditionalOperator</i> parameters.</note>
     */
    private String filterExpression;

    /**
     * The condition that specifies the key value(s) for items to be
     * retrieved by the <i>Query</i> action. <p>The condition must perform an
     * equality test on a single hash key value. The condition can also
     * perform one of several comparison tests on a single range key value.
     * <i>Query</i> can use <i>KeyConditionExpression</i> to retrieve one
     * item with a given hash and range key value, or several items that have
     * the same hash key value but different range key values. <p>The hash
     * key equality test is required, and must be specified in the following
     * format: <p> <code>hashAttributeName</code> <i>=</i>
     * <code>:hashval</code> <p>If you also want to provide a range key
     * condition, it must be combined using <i>AND</i> with the hash key
     * condition. Following is an example, using the <b>=</b> comparison
     * operator for the range key: <p> <code>hashAttributeName</code>
     * <i>=</i> <code>:hashval</code> <i>AND</i>
     * <code>rangeAttributeName</code> <i>=</i> <code>:rangeval</code>
     * <p>Valid comparisons for the range key condition are as follows: <ul>
     * <li> <p><code>rangeAttributeName</code> <i>=</i>
     * <code>:rangeval</code> - true if the range key is equal to
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><</i> <code>:rangeval</code> - true if the range key is less than
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><=</i> <code>:rangeval</code> - true if the range key is less than
     * or equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>></i> <code>:rangeval</code> -
     * true if the range key is greater than <code>:rangeval</code>. </li>
     * <li> <p><code>rangeAttributeName</code> <i>>=
     * </i><code>:rangeval</code> - true if the range key is greater than or
     * equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>BETWEEN</i>
     * <code>:rangeval1</code> <i>AND</i> <code>:rangeval2</code> - true if
     * the range key is greater than or equal to <code>:rangeval1</code>, and
     * less than or equal to <code>:rangeval2</code>. </li> <li>
     * <p><i>begins_with (</i><code>rangeAttributeName</code>,
     * <code>:rangeval</code><i>)</i> - true if the range key begins with a
     * particular operand. (You cannot use this function with a range key
     * that is of type Number.) Note that the function name
     * <code>begins_with</code> is case-sensitive. </li> </ul> <p>Use the
     * <i>ExpressionAttributeValues</i> parameter to replace tokens such as
     * <code>:hashval</code> and <code>:rangeval</code> with actual values at
     * runtime. <p>You can optionally use the <i>ExpressionAttributeNames</i>
     * parameter to replace the names of the hash and range attributes with
     * placeholder tokens. This option might be necessary if an attribute
     * name conflicts with a DynamoDB reserved word. For example, the
     * following <i>KeyConditionExpression</i> parameter causes an error
     * because <i>Size</i> is a reserved word: <ul> <li> <code>Size =
     * :myval</code> </li> </ul> <p>To work around this, define a placeholder
     * (such a <code>#S</code>) to represent the attribute name <i>Size</i>.
     * <i>KeyConditionExpression</i> then is as follows: <ul> <li> <code>#S =
     * :myval</code> </li> </ul> <p>For a list of reserved words, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>. <p>For more
     * information on <i>ExpressionAttributeNames</i> and
     * <i>ExpressionAttributeValues</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>. <note> <p><i>KeyConditionExpression</i>
     * replaces the legacy <i>KeyConditions</i> parameter. </note>
     */
    private String keyConditionExpression;

    /**
     * One or more substitution tokens for attribute names in an expression.
     * The following are some use cases for using
     * <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     * whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     * create a placeholder for repeating occurrences of an attribute name in
     * an expression. </li> <li> <p>To prevent special characters in an
     * attribute name from being misinterpreted in an expression. </li> </ul>
     * <p>Use the <b>#</b> character in an expression to dereference an
     * attribute name. For example, consider the following attribute name:
     * <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     * attribute conflicts with a reserved word, so it cannot be used
     * directly in an expression. (For the complete list of reserved words,
     * see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     * around this, you could specify the following for
     * <i>ExpressionAttributeNames</i>:
     * <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     * then use this substitution in an expression, as in this example:
     * <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     * with the <b>:</b> character are <i>expression attribute values</i>,
     * which are placeholders for the actual value at runtime.</note> <p>For
     * more information on expression attribute names, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     */
    private java.util.Map<String,String> expressionAttributeNames;

    /**
     * One or more values that can be substituted in an expression. <p>Use
     * the <b>:</b> (colon) character in an expression to dereference an
     * attribute value. For example, suppose that you wanted to check whether
     * the value of the <i>ProductStatus</i> attribute was one of the
     * following: <p><code>Available | Backordered | Discontinued</code>
     * <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     * follows: <p><code>{ ":avail":{"S":"Available"},
     * ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     * <p>You could then use these values in an expression, such as this:
     * <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     * information on expression attribute values, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     */
    private java.util.Map<String,AttributeValue> expressionAttributeValues;

    /**
     * Default constructor for a new QueryRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public QueryRequest() {}
    
    /**
     * Constructs a new QueryRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param tableName The name of the table containing the requested items.
     */
    public QueryRequest(String tableName) {
        setTableName(tableName);
    }

    /**
     * The name of the table containing the requested items.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     *
     * @return The name of the table containing the requested items.
     */
    public String getTableName() {
        return tableName;
    }
    
    /**
     * The name of the table containing the requested items.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     *
     * @param tableName The name of the table containing the requested items.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * The name of the table containing the requested items.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     *
     * @param tableName The name of the table containing the requested items.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     * The name of an index to query. This index can be any local secondary
     * index or global secondary index on the table. Note that if you use the
     * <i>IndexName</i> parameter, you must also provide <i>TableName.</i>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     *
     * @return The name of an index to query. This index can be any local secondary
     *         index or global secondary index on the table. Note that if you use the
     *         <i>IndexName</i> parameter, you must also provide <i>TableName.</i>
     */
    public String getIndexName() {
        return indexName;
    }
    
    /**
     * The name of an index to query. This index can be any local secondary
     * index or global secondary index on the table. Note that if you use the
     * <i>IndexName</i> parameter, you must also provide <i>TableName.</i>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     *
     * @param indexName The name of an index to query. This index can be any local secondary
     *         index or global secondary index on the table. Note that if you use the
     *         <i>IndexName</i> parameter, you must also provide <i>TableName.</i>
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    
    /**
     * The name of an index to query. This index can be any local secondary
     * index or global secondary index on the table. Note that if you use the
     * <i>IndexName</i> parameter, you must also provide <i>TableName.</i>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>3 - 255<br/>
     * <b>Pattern: </b>[a-zA-Z0-9_.-]+<br/>
     *
     * @param indexName The name of an index to query. This index can be any local secondary
     *         index or global secondary index on the table. Note that if you use the
     *         <i>IndexName</i> parameter, you must also provide <i>TableName.</i>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withIndexName(String indexName) {
        this.indexName = indexName;
        return this;
    }

    /**
     * The attributes to be returned in the result. You can retrieve all item
     * attributes, specific item attributes, the count of matching items, or
     * in the case of an index, some or all of the attributes projected into
     * the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     * the item attributes from the specified table or index. If you query a
     * local secondary index, then for each matching item in the index
     * DynamoDB will fetch the entire item from the parent table. If the
     * index is configured to project all item attributes, then all of the
     * data can be obtained from the local secondary index, and no fetching
     * is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     * Allowed only when querying an index. Retrieves all attributes that
     * have been projected into the index. If the index is configured to
     * project all attributes, this return value is equivalent to specifying
     * <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     * Returns the number of matching items, rather than the matching items
     * themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     * only the attributes listed in <i>AttributesToGet</i>. This return
     * value is equivalent to specifying <i>AttributesToGet</i> without
     * specifying any value for <i>Select</i>. <p>If you query a local
     * secondary index and request only attributes that are projected into
     * that index, the operation will read only the index and not the table.
     * If any of the requested attributes are not projected into the local
     * secondary index, DynamoDB will fetch each of these attributes from the
     * parent table. This extra fetching incurs additional throughput cost
     * and latency. <p>If you query a global secondary index, you can only
     * request attributes that are projected into the index. Global secondary
     * index queries cannot fetch attributes from the parent table. </li>
     * </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     * specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     * accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     * accessing an index. You cannot use both <i>Select</i> and
     * <i>AttributesToGet</i> together in a single request, unless the value
     * for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     * equivalent to specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     * parameter, then the value for <i>Select</i> can only be
     * <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     * will return an error.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ALL_ATTRIBUTES, ALL_PROJECTED_ATTRIBUTES, SPECIFIC_ATTRIBUTES, COUNT
     *
     * @return The attributes to be returned in the result. You can retrieve all item
     *         attributes, specific item attributes, the count of matching items, or
     *         in the case of an index, some or all of the attributes projected into
     *         the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     *         the item attributes from the specified table or index. If you query a
     *         local secondary index, then for each matching item in the index
     *         DynamoDB will fetch the entire item from the parent table. If the
     *         index is configured to project all item attributes, then all of the
     *         data can be obtained from the local secondary index, and no fetching
     *         is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     *         Allowed only when querying an index. Retrieves all attributes that
     *         have been projected into the index. If the index is configured to
     *         project all attributes, this return value is equivalent to specifying
     *         <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     *         Returns the number of matching items, rather than the matching items
     *         themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     *         only the attributes listed in <i>AttributesToGet</i>. This return
     *         value is equivalent to specifying <i>AttributesToGet</i> without
     *         specifying any value for <i>Select</i>. <p>If you query a local
     *         secondary index and request only attributes that are projected into
     *         that index, the operation will read only the index and not the table.
     *         If any of the requested attributes are not projected into the local
     *         secondary index, DynamoDB will fetch each of these attributes from the
     *         parent table. This extra fetching incurs additional throughput cost
     *         and latency. <p>If you query a global secondary index, you can only
     *         request attributes that are projected into the index. Global secondary
     *         index queries cannot fetch attributes from the parent table. </li>
     *         </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     *         specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     *         accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     *         accessing an index. You cannot use both <i>Select</i> and
     *         <i>AttributesToGet</i> together in a single request, unless the value
     *         for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     *         equivalent to specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     *         parameter, then the value for <i>Select</i> can only be
     *         <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     *         will return an error.</note>
     *
     * @see Select
     */
    public String getSelect() {
        return select;
    }
    
    /**
     * The attributes to be returned in the result. You can retrieve all item
     * attributes, specific item attributes, the count of matching items, or
     * in the case of an index, some or all of the attributes projected into
     * the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     * the item attributes from the specified table or index. If you query a
     * local secondary index, then for each matching item in the index
     * DynamoDB will fetch the entire item from the parent table. If the
     * index is configured to project all item attributes, then all of the
     * data can be obtained from the local secondary index, and no fetching
     * is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     * Allowed only when querying an index. Retrieves all attributes that
     * have been projected into the index. If the index is configured to
     * project all attributes, this return value is equivalent to specifying
     * <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     * Returns the number of matching items, rather than the matching items
     * themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     * only the attributes listed in <i>AttributesToGet</i>. This return
     * value is equivalent to specifying <i>AttributesToGet</i> without
     * specifying any value for <i>Select</i>. <p>If you query a local
     * secondary index and request only attributes that are projected into
     * that index, the operation will read only the index and not the table.
     * If any of the requested attributes are not projected into the local
     * secondary index, DynamoDB will fetch each of these attributes from the
     * parent table. This extra fetching incurs additional throughput cost
     * and latency. <p>If you query a global secondary index, you can only
     * request attributes that are projected into the index. Global secondary
     * index queries cannot fetch attributes from the parent table. </li>
     * </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     * specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     * accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     * accessing an index. You cannot use both <i>Select</i> and
     * <i>AttributesToGet</i> together in a single request, unless the value
     * for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     * equivalent to specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     * parameter, then the value for <i>Select</i> can only be
     * <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     * will return an error.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ALL_ATTRIBUTES, ALL_PROJECTED_ATTRIBUTES, SPECIFIC_ATTRIBUTES, COUNT
     *
     * @param select The attributes to be returned in the result. You can retrieve all item
     *         attributes, specific item attributes, the count of matching items, or
     *         in the case of an index, some or all of the attributes projected into
     *         the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     *         the item attributes from the specified table or index. If you query a
     *         local secondary index, then for each matching item in the index
     *         DynamoDB will fetch the entire item from the parent table. If the
     *         index is configured to project all item attributes, then all of the
     *         data can be obtained from the local secondary index, and no fetching
     *         is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     *         Allowed only when querying an index. Retrieves all attributes that
     *         have been projected into the index. If the index is configured to
     *         project all attributes, this return value is equivalent to specifying
     *         <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     *         Returns the number of matching items, rather than the matching items
     *         themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     *         only the attributes listed in <i>AttributesToGet</i>. This return
     *         value is equivalent to specifying <i>AttributesToGet</i> without
     *         specifying any value for <i>Select</i>. <p>If you query a local
     *         secondary index and request only attributes that are projected into
     *         that index, the operation will read only the index and not the table.
     *         If any of the requested attributes are not projected into the local
     *         secondary index, DynamoDB will fetch each of these attributes from the
     *         parent table. This extra fetching incurs additional throughput cost
     *         and latency. <p>If you query a global secondary index, you can only
     *         request attributes that are projected into the index. Global secondary
     *         index queries cannot fetch attributes from the parent table. </li>
     *         </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     *         specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     *         accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     *         accessing an index. You cannot use both <i>Select</i> and
     *         <i>AttributesToGet</i> together in a single request, unless the value
     *         for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     *         equivalent to specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     *         parameter, then the value for <i>Select</i> can only be
     *         <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     *         will return an error.</note>
     *
     * @see Select
     */
    public void setSelect(String select) {
        this.select = select;
    }
    
    /**
     * The attributes to be returned in the result. You can retrieve all item
     * attributes, specific item attributes, the count of matching items, or
     * in the case of an index, some or all of the attributes projected into
     * the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     * the item attributes from the specified table or index. If you query a
     * local secondary index, then for each matching item in the index
     * DynamoDB will fetch the entire item from the parent table. If the
     * index is configured to project all item attributes, then all of the
     * data can be obtained from the local secondary index, and no fetching
     * is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     * Allowed only when querying an index. Retrieves all attributes that
     * have been projected into the index. If the index is configured to
     * project all attributes, this return value is equivalent to specifying
     * <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     * Returns the number of matching items, rather than the matching items
     * themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     * only the attributes listed in <i>AttributesToGet</i>. This return
     * value is equivalent to specifying <i>AttributesToGet</i> without
     * specifying any value for <i>Select</i>. <p>If you query a local
     * secondary index and request only attributes that are projected into
     * that index, the operation will read only the index and not the table.
     * If any of the requested attributes are not projected into the local
     * secondary index, DynamoDB will fetch each of these attributes from the
     * parent table. This extra fetching incurs additional throughput cost
     * and latency. <p>If you query a global secondary index, you can only
     * request attributes that are projected into the index. Global secondary
     * index queries cannot fetch attributes from the parent table. </li>
     * </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     * specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     * accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     * accessing an index. You cannot use both <i>Select</i> and
     * <i>AttributesToGet</i> together in a single request, unless the value
     * for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     * equivalent to specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     * parameter, then the value for <i>Select</i> can only be
     * <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     * will return an error.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ALL_ATTRIBUTES, ALL_PROJECTED_ATTRIBUTES, SPECIFIC_ATTRIBUTES, COUNT
     *
     * @param select The attributes to be returned in the result. You can retrieve all item
     *         attributes, specific item attributes, the count of matching items, or
     *         in the case of an index, some or all of the attributes projected into
     *         the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     *         the item attributes from the specified table or index. If you query a
     *         local secondary index, then for each matching item in the index
     *         DynamoDB will fetch the entire item from the parent table. If the
     *         index is configured to project all item attributes, then all of the
     *         data can be obtained from the local secondary index, and no fetching
     *         is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     *         Allowed only when querying an index. Retrieves all attributes that
     *         have been projected into the index. If the index is configured to
     *         project all attributes, this return value is equivalent to specifying
     *         <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     *         Returns the number of matching items, rather than the matching items
     *         themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     *         only the attributes listed in <i>AttributesToGet</i>. This return
     *         value is equivalent to specifying <i>AttributesToGet</i> without
     *         specifying any value for <i>Select</i>. <p>If you query a local
     *         secondary index and request only attributes that are projected into
     *         that index, the operation will read only the index and not the table.
     *         If any of the requested attributes are not projected into the local
     *         secondary index, DynamoDB will fetch each of these attributes from the
     *         parent table. This extra fetching incurs additional throughput cost
     *         and latency. <p>If you query a global secondary index, you can only
     *         request attributes that are projected into the index. Global secondary
     *         index queries cannot fetch attributes from the parent table. </li>
     *         </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     *         specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     *         accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     *         accessing an index. You cannot use both <i>Select</i> and
     *         <i>AttributesToGet</i> together in a single request, unless the value
     *         for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     *         equivalent to specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     *         parameter, then the value for <i>Select</i> can only be
     *         <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     *         will return an error.</note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see Select
     */
    public QueryRequest withSelect(String select) {
        this.select = select;
        return this;
    }

    /**
     * The attributes to be returned in the result. You can retrieve all item
     * attributes, specific item attributes, the count of matching items, or
     * in the case of an index, some or all of the attributes projected into
     * the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     * the item attributes from the specified table or index. If you query a
     * local secondary index, then for each matching item in the index
     * DynamoDB will fetch the entire item from the parent table. If the
     * index is configured to project all item attributes, then all of the
     * data can be obtained from the local secondary index, and no fetching
     * is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     * Allowed only when querying an index. Retrieves all attributes that
     * have been projected into the index. If the index is configured to
     * project all attributes, this return value is equivalent to specifying
     * <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     * Returns the number of matching items, rather than the matching items
     * themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     * only the attributes listed in <i>AttributesToGet</i>. This return
     * value is equivalent to specifying <i>AttributesToGet</i> without
     * specifying any value for <i>Select</i>. <p>If you query a local
     * secondary index and request only attributes that are projected into
     * that index, the operation will read only the index and not the table.
     * If any of the requested attributes are not projected into the local
     * secondary index, DynamoDB will fetch each of these attributes from the
     * parent table. This extra fetching incurs additional throughput cost
     * and latency. <p>If you query a global secondary index, you can only
     * request attributes that are projected into the index. Global secondary
     * index queries cannot fetch attributes from the parent table. </li>
     * </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     * specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     * accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     * accessing an index. You cannot use both <i>Select</i> and
     * <i>AttributesToGet</i> together in a single request, unless the value
     * for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     * equivalent to specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     * parameter, then the value for <i>Select</i> can only be
     * <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     * will return an error.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ALL_ATTRIBUTES, ALL_PROJECTED_ATTRIBUTES, SPECIFIC_ATTRIBUTES, COUNT
     *
     * @param select The attributes to be returned in the result. You can retrieve all item
     *         attributes, specific item attributes, the count of matching items, or
     *         in the case of an index, some or all of the attributes projected into
     *         the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     *         the item attributes from the specified table or index. If you query a
     *         local secondary index, then for each matching item in the index
     *         DynamoDB will fetch the entire item from the parent table. If the
     *         index is configured to project all item attributes, then all of the
     *         data can be obtained from the local secondary index, and no fetching
     *         is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     *         Allowed only when querying an index. Retrieves all attributes that
     *         have been projected into the index. If the index is configured to
     *         project all attributes, this return value is equivalent to specifying
     *         <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     *         Returns the number of matching items, rather than the matching items
     *         themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     *         only the attributes listed in <i>AttributesToGet</i>. This return
     *         value is equivalent to specifying <i>AttributesToGet</i> without
     *         specifying any value for <i>Select</i>. <p>If you query a local
     *         secondary index and request only attributes that are projected into
     *         that index, the operation will read only the index and not the table.
     *         If any of the requested attributes are not projected into the local
     *         secondary index, DynamoDB will fetch each of these attributes from the
     *         parent table. This extra fetching incurs additional throughput cost
     *         and latency. <p>If you query a global secondary index, you can only
     *         request attributes that are projected into the index. Global secondary
     *         index queries cannot fetch attributes from the parent table. </li>
     *         </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     *         specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     *         accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     *         accessing an index. You cannot use both <i>Select</i> and
     *         <i>AttributesToGet</i> together in a single request, unless the value
     *         for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     *         equivalent to specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     *         parameter, then the value for <i>Select</i> can only be
     *         <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     *         will return an error.</note>
     *
     * @see Select
     */
    public void setSelect(Select select) {
        this.select = select.toString();
    }
    
    /**
     * The attributes to be returned in the result. You can retrieve all item
     * attributes, specific item attributes, the count of matching items, or
     * in the case of an index, some or all of the attributes projected into
     * the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     * the item attributes from the specified table or index. If you query a
     * local secondary index, then for each matching item in the index
     * DynamoDB will fetch the entire item from the parent table. If the
     * index is configured to project all item attributes, then all of the
     * data can be obtained from the local secondary index, and no fetching
     * is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     * Allowed only when querying an index. Retrieves all attributes that
     * have been projected into the index. If the index is configured to
     * project all attributes, this return value is equivalent to specifying
     * <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     * Returns the number of matching items, rather than the matching items
     * themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     * only the attributes listed in <i>AttributesToGet</i>. This return
     * value is equivalent to specifying <i>AttributesToGet</i> without
     * specifying any value for <i>Select</i>. <p>If you query a local
     * secondary index and request only attributes that are projected into
     * that index, the operation will read only the index and not the table.
     * If any of the requested attributes are not projected into the local
     * secondary index, DynamoDB will fetch each of these attributes from the
     * parent table. This extra fetching incurs additional throughput cost
     * and latency. <p>If you query a global secondary index, you can only
     * request attributes that are projected into the index. Global secondary
     * index queries cannot fetch attributes from the parent table. </li>
     * </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     * specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     * accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     * accessing an index. You cannot use both <i>Select</i> and
     * <i>AttributesToGet</i> together in a single request, unless the value
     * for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     * equivalent to specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     * parameter, then the value for <i>Select</i> can only be
     * <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     * will return an error.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ALL_ATTRIBUTES, ALL_PROJECTED_ATTRIBUTES, SPECIFIC_ATTRIBUTES, COUNT
     *
     * @param select The attributes to be returned in the result. You can retrieve all item
     *         attributes, specific item attributes, the count of matching items, or
     *         in the case of an index, some or all of the attributes projected into
     *         the index. <ul> <li> <p><code>ALL_ATTRIBUTES</code> - Returns all of
     *         the item attributes from the specified table or index. If you query a
     *         local secondary index, then for each matching item in the index
     *         DynamoDB will fetch the entire item from the parent table. If the
     *         index is configured to project all item attributes, then all of the
     *         data can be obtained from the local secondary index, and no fetching
     *         is required. </li> <li> <p><code>ALL_PROJECTED_ATTRIBUTES</code> -
     *         Allowed only when querying an index. Retrieves all attributes that
     *         have been projected into the index. If the index is configured to
     *         project all attributes, this return value is equivalent to specifying
     *         <code>ALL_ATTRIBUTES</code>. </li> <li> <p><code>COUNT</code> -
     *         Returns the number of matching items, rather than the matching items
     *         themselves. </li> <li> <p> <code>SPECIFIC_ATTRIBUTES</code> - Returns
     *         only the attributes listed in <i>AttributesToGet</i>. This return
     *         value is equivalent to specifying <i>AttributesToGet</i> without
     *         specifying any value for <i>Select</i>. <p>If you query a local
     *         secondary index and request only attributes that are projected into
     *         that index, the operation will read only the index and not the table.
     *         If any of the requested attributes are not projected into the local
     *         secondary index, DynamoDB will fetch each of these attributes from the
     *         parent table. This extra fetching incurs additional throughput cost
     *         and latency. <p>If you query a global secondary index, you can only
     *         request attributes that are projected into the index. Global secondary
     *         index queries cannot fetch attributes from the parent table. </li>
     *         </ul> <p>If neither <i>Select</i> nor <i>AttributesToGet</i> are
     *         specified, DynamoDB defaults to <code>ALL_ATTRIBUTES</code> when
     *         accessing a table, and <code>ALL_PROJECTED_ATTRIBUTES</code> when
     *         accessing an index. You cannot use both <i>Select</i> and
     *         <i>AttributesToGet</i> together in a single request, unless the value
     *         for <i>Select</i> is <code>SPECIFIC_ATTRIBUTES</code>. (This usage is
     *         equivalent to specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <note><p>If you use the <i>ProjectionExpression</i>
     *         parameter, then the value for <i>Select</i> can only be
     *         <code>SPECIFIC_ATTRIBUTES</code>. Any other value for <i>Select</i>
     *         will return an error.</note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see Select
     */
    public QueryRequest withSelect(Select select) {
        this.select = select.toString();
        return this;
    }

    /**
     * <important><p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>ProjectionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. <p>This parameter allows you to retrieve attributes of type
     * List or Map; however, it cannot retrieve individual elements within a
     * List or a Map.</important> <p>The names of one or more attributes to
     * retrieve. If no attribute names are provided, then all attributes will
     * be returned. If any of the requested attributes are not found, they
     * will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     * no effect on provisioned throughput consumption. DynamoDB determines
     * capacity units consumed based on item size, not on the amount of data
     * that is returned to an application. <p>You cannot use both
     * <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     * request, <i>unless</i> the value for <i>Select</i> is
     * <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     * specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <p>If you query a local secondary index and request
     * only attributes that are projected into that index, the operation will
     * read only the index and not the table. If any of the requested
     * attributes are not projected into the local secondary index, DynamoDB
     * will fetch each of these attributes from the parent table. This extra
     * fetching incurs additional throughput cost and latency. <p>If you
     * query a global secondary index, you can only request attributes that
     * are projected into the index. Global secondary index queries cannot
     * fetch attributes from the parent table.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @return <important><p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>ProjectionExpression</i> instead. Do
     *         not combine legacy parameters and expression parameters in a single
     *         API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. <p>This parameter allows you to retrieve attributes of type
     *         List or Map; however, it cannot retrieve individual elements within a
     *         List or a Map.</important> <p>The names of one or more attributes to
     *         retrieve. If no attribute names are provided, then all attributes will
     *         be returned. If any of the requested attributes are not found, they
     *         will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     *         no effect on provisioned throughput consumption. DynamoDB determines
     *         capacity units consumed based on item size, not on the amount of data
     *         that is returned to an application. <p>You cannot use both
     *         <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     *         request, <i>unless</i> the value for <i>Select</i> is
     *         <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     *         specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <p>If you query a local secondary index and request
     *         only attributes that are projected into that index, the operation will
     *         read only the index and not the table. If any of the requested
     *         attributes are not projected into the local secondary index, DynamoDB
     *         will fetch each of these attributes from the parent table. This extra
     *         fetching incurs additional throughput cost and latency. <p>If you
     *         query a global secondary index, you can only request attributes that
     *         are projected into the index. Global secondary index queries cannot
     *         fetch attributes from the parent table.
     */
    public java.util.List<String> getAttributesToGet() {
        return attributesToGet;
    }
    
    /**
     * <important><p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>ProjectionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. <p>This parameter allows you to retrieve attributes of type
     * List or Map; however, it cannot retrieve individual elements within a
     * List or a Map.</important> <p>The names of one or more attributes to
     * retrieve. If no attribute names are provided, then all attributes will
     * be returned. If any of the requested attributes are not found, they
     * will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     * no effect on provisioned throughput consumption. DynamoDB determines
     * capacity units consumed based on item size, not on the amount of data
     * that is returned to an application. <p>You cannot use both
     * <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     * request, <i>unless</i> the value for <i>Select</i> is
     * <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     * specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <p>If you query a local secondary index and request
     * only attributes that are projected into that index, the operation will
     * read only the index and not the table. If any of the requested
     * attributes are not projected into the local secondary index, DynamoDB
     * will fetch each of these attributes from the parent table. This extra
     * fetching incurs additional throughput cost and latency. <p>If you
     * query a global secondary index, you can only request attributes that
     * are projected into the index. Global secondary index queries cannot
     * fetch attributes from the parent table.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param attributesToGet <important><p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>ProjectionExpression</i> instead. Do
     *         not combine legacy parameters and expression parameters in a single
     *         API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. <p>This parameter allows you to retrieve attributes of type
     *         List or Map; however, it cannot retrieve individual elements within a
     *         List or a Map.</important> <p>The names of one or more attributes to
     *         retrieve. If no attribute names are provided, then all attributes will
     *         be returned. If any of the requested attributes are not found, they
     *         will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     *         no effect on provisioned throughput consumption. DynamoDB determines
     *         capacity units consumed based on item size, not on the amount of data
     *         that is returned to an application. <p>You cannot use both
     *         <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     *         request, <i>unless</i> the value for <i>Select</i> is
     *         <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     *         specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <p>If you query a local secondary index and request
     *         only attributes that are projected into that index, the operation will
     *         read only the index and not the table. If any of the requested
     *         attributes are not projected into the local secondary index, DynamoDB
     *         will fetch each of these attributes from the parent table. This extra
     *         fetching incurs additional throughput cost and latency. <p>If you
     *         query a global secondary index, you can only request attributes that
     *         are projected into the index. Global secondary index queries cannot
     *         fetch attributes from the parent table.
     */
    public void setAttributesToGet(java.util.Collection<String> attributesToGet) {
        if (attributesToGet == null) {
            this.attributesToGet = null;
            return;
        }
        com.amazonaws.internal.ListWithAutoConstructFlag<String> attributesToGetCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<String>(attributesToGet.size());
        attributesToGetCopy.addAll(attributesToGet);
        this.attributesToGet = attributesToGetCopy;
    }
    
    /**
     * <important><p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>ProjectionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. <p>This parameter allows you to retrieve attributes of type
     * List or Map; however, it cannot retrieve individual elements within a
     * List or a Map.</important> <p>The names of one or more attributes to
     * retrieve. If no attribute names are provided, then all attributes will
     * be returned. If any of the requested attributes are not found, they
     * will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     * no effect on provisioned throughput consumption. DynamoDB determines
     * capacity units consumed based on item size, not on the amount of data
     * that is returned to an application. <p>You cannot use both
     * <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     * request, <i>unless</i> the value for <i>Select</i> is
     * <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     * specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <p>If you query a local secondary index and request
     * only attributes that are projected into that index, the operation will
     * read only the index and not the table. If any of the requested
     * attributes are not projected into the local secondary index, DynamoDB
     * will fetch each of these attributes from the parent table. This extra
     * fetching incurs additional throughput cost and latency. <p>If you
     * query a global secondary index, you can only request attributes that
     * are projected into the index. Global secondary index queries cannot
     * fetch attributes from the parent table.
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if
     * any). Use {@link #setAttributesToGet(java.util.Collection)} or {@link
     * #withAttributesToGet(java.util.Collection)} if you want to override
     * the existing values.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param attributesToGet <important><p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>ProjectionExpression</i> instead. Do
     *         not combine legacy parameters and expression parameters in a single
     *         API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. <p>This parameter allows you to retrieve attributes of type
     *         List or Map; however, it cannot retrieve individual elements within a
     *         List or a Map.</important> <p>The names of one or more attributes to
     *         retrieve. If no attribute names are provided, then all attributes will
     *         be returned. If any of the requested attributes are not found, they
     *         will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     *         no effect on provisioned throughput consumption. DynamoDB determines
     *         capacity units consumed based on item size, not on the amount of data
     *         that is returned to an application. <p>You cannot use both
     *         <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     *         request, <i>unless</i> the value for <i>Select</i> is
     *         <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     *         specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <p>If you query a local secondary index and request
     *         only attributes that are projected into that index, the operation will
     *         read only the index and not the table. If any of the requested
     *         attributes are not projected into the local secondary index, DynamoDB
     *         will fetch each of these attributes from the parent table. This extra
     *         fetching incurs additional throughput cost and latency. <p>If you
     *         query a global secondary index, you can only request attributes that
     *         are projected into the index. Global secondary index queries cannot
     *         fetch attributes from the parent table.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withAttributesToGet(String... attributesToGet) {
        if (getAttributesToGet() == null) setAttributesToGet(new java.util.ArrayList<String>(attributesToGet.length));
        for (String value : attributesToGet) {
            getAttributesToGet().add(value);
        }
        return this;
    }
    
    /**
     * <important><p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>ProjectionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. <p>This parameter allows you to retrieve attributes of type
     * List or Map; however, it cannot retrieve individual elements within a
     * List or a Map.</important> <p>The names of one or more attributes to
     * retrieve. If no attribute names are provided, then all attributes will
     * be returned. If any of the requested attributes are not found, they
     * will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     * no effect on provisioned throughput consumption. DynamoDB determines
     * capacity units consumed based on item size, not on the amount of data
     * that is returned to an application. <p>You cannot use both
     * <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     * request, <i>unless</i> the value for <i>Select</i> is
     * <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     * specifying <i>AttributesToGet</i> without any value for
     * <i>Select</i>.) <p>If you query a local secondary index and request
     * only attributes that are projected into that index, the operation will
     * read only the index and not the table. If any of the requested
     * attributes are not projected into the local secondary index, DynamoDB
     * will fetch each of these attributes from the parent table. This extra
     * fetching incurs additional throughput cost and latency. <p>If you
     * query a global secondary index, you can only request attributes that
     * are projected into the index. Global secondary index queries cannot
     * fetch attributes from the parent table.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - <br/>
     *
     * @param attributesToGet <important><p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>ProjectionExpression</i> instead. Do
     *         not combine legacy parameters and expression parameters in a single
     *         API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. <p>This parameter allows you to retrieve attributes of type
     *         List or Map; however, it cannot retrieve individual elements within a
     *         List or a Map.</important> <p>The names of one or more attributes to
     *         retrieve. If no attribute names are provided, then all attributes will
     *         be returned. If any of the requested attributes are not found, they
     *         will not appear in the result. <p>Note that <i>AttributesToGet</i> has
     *         no effect on provisioned throughput consumption. DynamoDB determines
     *         capacity units consumed based on item size, not on the amount of data
     *         that is returned to an application. <p>You cannot use both
     *         <i>AttributesToGet</i> and <i>Select</i> together in a <i>Query</i>
     *         request, <i>unless</i> the value for <i>Select</i> is
     *         <code>SPECIFIC_ATTRIBUTES</code>. (This usage is equivalent to
     *         specifying <i>AttributesToGet</i> without any value for
     *         <i>Select</i>.) <p>If you query a local secondary index and request
     *         only attributes that are projected into that index, the operation will
     *         read only the index and not the table. If any of the requested
     *         attributes are not projected into the local secondary index, DynamoDB
     *         will fetch each of these attributes from the parent table. This extra
     *         fetching incurs additional throughput cost and latency. <p>If you
     *         query a global secondary index, you can only request attributes that
     *         are projected into the index. Global secondary index queries cannot
     *         fetch attributes from the parent table.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withAttributesToGet(java.util.Collection<String> attributesToGet) {
        if (attributesToGet == null) {
            this.attributesToGet = null;
        } else {
            com.amazonaws.internal.ListWithAutoConstructFlag<String> attributesToGetCopy = new com.amazonaws.internal.ListWithAutoConstructFlag<String>(attributesToGet.size());
            attributesToGetCopy.addAll(attributesToGet);
            this.attributesToGet = attributesToGetCopy;
        }

        return this;
    }

    /**
     * The maximum number of items to evaluate (not necessarily the number of
     * matching items). If DynamoDB processes the number of items up to the
     * limit while processing the results, it stops the operation and returns
     * the matching values up to that point, and a key in
     * <i>LastEvaluatedKey</i> to apply in a subsequent operation, so that
     * you can pick up where you left off. Also, if the processed data set
     * size exceeds 1 MB before DynamoDB reaches this limit, it stops the
     * operation and returns the matching values up to the limit, and a key
     * in <i>LastEvaluatedKey</i> to apply in a subsequent operation to
     * continue the operation. For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html">Query
     * and Scan</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - <br/>
     *
     * @return The maximum number of items to evaluate (not necessarily the number of
     *         matching items). If DynamoDB processes the number of items up to the
     *         limit while processing the results, it stops the operation and returns
     *         the matching values up to that point, and a key in
     *         <i>LastEvaluatedKey</i> to apply in a subsequent operation, so that
     *         you can pick up where you left off. Also, if the processed data set
     *         size exceeds 1 MB before DynamoDB reaches this limit, it stops the
     *         operation and returns the matching values up to the limit, and a key
     *         in <i>LastEvaluatedKey</i> to apply in a subsequent operation to
     *         continue the operation. For more information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html">Query
     *         and Scan</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     */
    public Integer getLimit() {
        return limit;
    }
    
    /**
     * The maximum number of items to evaluate (not necessarily the number of
     * matching items). If DynamoDB processes the number of items up to the
     * limit while processing the results, it stops the operation and returns
     * the matching values up to that point, and a key in
     * <i>LastEvaluatedKey</i> to apply in a subsequent operation, so that
     * you can pick up where you left off. Also, if the processed data set
     * size exceeds 1 MB before DynamoDB reaches this limit, it stops the
     * operation and returns the matching values up to the limit, and a key
     * in <i>LastEvaluatedKey</i> to apply in a subsequent operation to
     * continue the operation. For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html">Query
     * and Scan</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - <br/>
     *
     * @param limit The maximum number of items to evaluate (not necessarily the number of
     *         matching items). If DynamoDB processes the number of items up to the
     *         limit while processing the results, it stops the operation and returns
     *         the matching values up to that point, and a key in
     *         <i>LastEvaluatedKey</i> to apply in a subsequent operation, so that
     *         you can pick up where you left off. Also, if the processed data set
     *         size exceeds 1 MB before DynamoDB reaches this limit, it stops the
     *         operation and returns the matching values up to the limit, and a key
     *         in <i>LastEvaluatedKey</i> to apply in a subsequent operation to
     *         continue the operation. For more information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html">Query
     *         and Scan</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    /**
     * The maximum number of items to evaluate (not necessarily the number of
     * matching items). If DynamoDB processes the number of items up to the
     * limit while processing the results, it stops the operation and returns
     * the matching values up to that point, and a key in
     * <i>LastEvaluatedKey</i> to apply in a subsequent operation, so that
     * you can pick up where you left off. Also, if the processed data set
     * size exceeds 1 MB before DynamoDB reaches this limit, it stops the
     * operation and returns the matching values up to the limit, and a key
     * in <i>LastEvaluatedKey</i> to apply in a subsequent operation to
     * continue the operation. For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html">Query
     * and Scan</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - <br/>
     *
     * @param limit The maximum number of items to evaluate (not necessarily the number of
     *         matching items). If DynamoDB processes the number of items up to the
     *         limit while processing the results, it stops the operation and returns
     *         the matching values up to that point, and a key in
     *         <i>LastEvaluatedKey</i> to apply in a subsequent operation, so that
     *         you can pick up where you left off. Also, if the processed data set
     *         size exceeds 1 MB before DynamoDB reaches this limit, it stops the
     *         operation and returns the matching values up to the limit, and a key
     *         in <i>LastEvaluatedKey</i> to apply in a subsequent operation to
     *         continue the operation. For more information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html">Query
     *         and Scan</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * A value that if set to <code>true</code>, then the operation uses
     * strongly consistent reads; otherwise, eventually consistent reads are
     * used. <p>Strongly consistent reads are not supported on global
     * secondary indexes. If you query a global secondary index with
     * <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     * error message.
     *
     * @return A value that if set to <code>true</code>, then the operation uses
     *         strongly consistent reads; otherwise, eventually consistent reads are
     *         used. <p>Strongly consistent reads are not supported on global
     *         secondary indexes. If you query a global secondary index with
     *         <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     *         error message.
     */
    public Boolean isConsistentRead() {
        return consistentRead;
    }
    
    /**
     * A value that if set to <code>true</code>, then the operation uses
     * strongly consistent reads; otherwise, eventually consistent reads are
     * used. <p>Strongly consistent reads are not supported on global
     * secondary indexes. If you query a global secondary index with
     * <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     * error message.
     *
     * @param consistentRead A value that if set to <code>true</code>, then the operation uses
     *         strongly consistent reads; otherwise, eventually consistent reads are
     *         used. <p>Strongly consistent reads are not supported on global
     *         secondary indexes. If you query a global secondary index with
     *         <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     *         error message.
     */
    public void setConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
    }
    
    /**
     * A value that if set to <code>true</code>, then the operation uses
     * strongly consistent reads; otherwise, eventually consistent reads are
     * used. <p>Strongly consistent reads are not supported on global
     * secondary indexes. If you query a global secondary index with
     * <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     * error message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param consistentRead A value that if set to <code>true</code>, then the operation uses
     *         strongly consistent reads; otherwise, eventually consistent reads are
     *         used. <p>Strongly consistent reads are not supported on global
     *         secondary indexes. If you query a global secondary index with
     *         <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     *         error message.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
        return this;
    }

    /**
     * A value that if set to <code>true</code>, then the operation uses
     * strongly consistent reads; otherwise, eventually consistent reads are
     * used. <p>Strongly consistent reads are not supported on global
     * secondary indexes. If you query a global secondary index with
     * <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     * error message.
     *
     * @return A value that if set to <code>true</code>, then the operation uses
     *         strongly consistent reads; otherwise, eventually consistent reads are
     *         used. <p>Strongly consistent reads are not supported on global
     *         secondary indexes. If you query a global secondary index with
     *         <i>ConsistentRead</i> set to <code>true</code>, you will receive an
     *         error message.
     */
    public Boolean getConsistentRead() {
        return consistentRead;
    }

    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>KeyConditionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>The selection criteria for the query. For a
     * query on a table, you can have conditions only on the table primary
     * key attributes. You must provide the hash key attribute name and value
     * as an <code>EQ</code> condition. You can optionally provide a second
     * condition, referring to the range key attribute. <note> <p>If you
     * don't provide a range key condition, all of the items that match the
     * hash key will be retrieved. If a <i>FilterExpression</i> or
     * <i>QueryFilter</i> is present, it will be applied after the items are
     * retrieved.</note> <p>For a query on an index, you can have conditions
     * only on the index key attributes. You must provide the index hash
     * attribute name and value as an <code>EQ</code> condition. You can
     * optionally provide a second condition, referring to the index key
     * range attribute. <p>Each <i>KeyConditions</i> element consists of an
     * attribute name to compare, along with the following: <ul> <li>
     * <p><i>AttributeValueList</i> - One or more values to evaluate against
     * the supplied attribute. The number of values in the list depends on
     * the <i>ComparisonOperator</i> being used. <p>For type Number, value
     * comparisons are numeric. <p>String value comparisons for greater than,
     * equals, or less than are based on ASCII character code values. For
     * example, <code>a</code> is greater than <code>A</code>, and
     * <code>a</code> is greater than <code>B</code>. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. </li> <li>
     * <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     * for example, equals, greater than, less than, and so on. <p>For
     * <i>KeyConditions</i>, only the following comparison operators are
     * supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     * BETWEEN</code> <p>The following are descriptions of these comparison
     * operators. <ul> <li> <p><code>EQ</code> : Equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one specified in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     * <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     * can contain only one <i>AttributeValue</i> element of type String,
     * Number, or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GE</code> : Greater than or equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> element of type String, Number, or Binary (not a
     * set type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     * contain only one <i>AttributeValue</i> element of type String, Number,
     * or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     * a prefix. <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String or Binary (not a Number or a set
     * type). The target attribute of the comparison must be of type String
     * or Binary (not a Number or a set type). <p/> </li> <li>
     * <p><code>BETWEEN</code> : Greater than or equal to the first value,
     * and less than or equal to the second value.
     * <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     * elements of the same type, either String, Number, or Binary (not a set
     * type). A target attribute matches if the target value is greater than,
     * or equal to, the first element and less than, or equal to, the second
     * element. If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not compare to
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     * usage examples of <i>AttributeValueList</i> and
     * <i>ComparisonOperator</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     * Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     * Guide</i>.
     *
     * @return <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>KeyConditionExpression</i> instead. Do
     *         not combine legacy parameters and expression parameters in a single
     *         API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>The selection criteria for the query. For a
     *         query on a table, you can have conditions only on the table primary
     *         key attributes. You must provide the hash key attribute name and value
     *         as an <code>EQ</code> condition. You can optionally provide a second
     *         condition, referring to the range key attribute. <note> <p>If you
     *         don't provide a range key condition, all of the items that match the
     *         hash key will be retrieved. If a <i>FilterExpression</i> or
     *         <i>QueryFilter</i> is present, it will be applied after the items are
     *         retrieved.</note> <p>For a query on an index, you can have conditions
     *         only on the index key attributes. You must provide the index hash
     *         attribute name and value as an <code>EQ</code> condition. You can
     *         optionally provide a second condition, referring to the index key
     *         range attribute. <p>Each <i>KeyConditions</i> element consists of an
     *         attribute name to compare, along with the following: <ul> <li>
     *         <p><i>AttributeValueList</i> - One or more values to evaluate against
     *         the supplied attribute. The number of values in the list depends on
     *         the <i>ComparisonOperator</i> being used. <p>For type Number, value
     *         comparisons are numeric. <p>String value comparisons for greater than,
     *         equals, or less than are based on ASCII character code values. For
     *         example, <code>a</code> is greater than <code>A</code>, and
     *         <code>a</code> is greater than <code>B</code>. For a list of code
     *         values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *         <p>For Binary, DynamoDB treats each byte of the binary data as
     *         unsigned when it compares binary values. </li> <li>
     *         <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     *         for example, equals, greater than, less than, and so on. <p>For
     *         <i>KeyConditions</i>, only the following comparison operators are
     *         supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     *         BETWEEN</code> <p>The following are descriptions of these comparison
     *         operators. <ul> <li> <p><code>EQ</code> : Equal.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String, Number, or Binary (not a set
     *         type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one specified in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     *         <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     *         can contain only one <i>AttributeValue</i> element of type String,
     *         Number, or Binary (not a set type). If an item contains an
     *         <i>AttributeValue</i> element of a different type than the one
     *         provided in the request, the value does not match. For example,
     *         <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     *         <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     *         "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String, Number, or Binary (not a set
     *         type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>GE</code> : Greater than or equal.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> element of type String, Number, or Binary (not a
     *         set type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     *         contain only one <i>AttributeValue</i> element of type String, Number,
     *         or Binary (not a set type). If an item contains an
     *         <i>AttributeValue</i> element of a different type than the one
     *         provided in the request, the value does not match. For example,
     *         <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     *         <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     *         "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     *         a prefix. <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String or Binary (not a Number or a set
     *         type). The target attribute of the comparison must be of type String
     *         or Binary (not a Number or a set type). <p/> </li> <li>
     *         <p><code>BETWEEN</code> : Greater than or equal to the first value,
     *         and less than or equal to the second value.
     *         <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     *         elements of the same type, either String, Number, or Binary (not a set
     *         type). A target attribute matches if the target value is greater than,
     *         or equal to, the first element and less than, or equal to, the second
     *         element. If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not compare to
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     *         usage examples of <i>AttributeValueList</i> and
     *         <i>ComparisonOperator</i>, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     *         Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     *         Guide</i>.
     */
    public java.util.Map<String,Condition> getKeyConditions() {
        
        return keyConditions;
    }
    
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>KeyConditionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>The selection criteria for the query. For a
     * query on a table, you can have conditions only on the table primary
     * key attributes. You must provide the hash key attribute name and value
     * as an <code>EQ</code> condition. You can optionally provide a second
     * condition, referring to the range key attribute. <note> <p>If you
     * don't provide a range key condition, all of the items that match the
     * hash key will be retrieved. If a <i>FilterExpression</i> or
     * <i>QueryFilter</i> is present, it will be applied after the items are
     * retrieved.</note> <p>For a query on an index, you can have conditions
     * only on the index key attributes. You must provide the index hash
     * attribute name and value as an <code>EQ</code> condition. You can
     * optionally provide a second condition, referring to the index key
     * range attribute. <p>Each <i>KeyConditions</i> element consists of an
     * attribute name to compare, along with the following: <ul> <li>
     * <p><i>AttributeValueList</i> - One or more values to evaluate against
     * the supplied attribute. The number of values in the list depends on
     * the <i>ComparisonOperator</i> being used. <p>For type Number, value
     * comparisons are numeric. <p>String value comparisons for greater than,
     * equals, or less than are based on ASCII character code values. For
     * example, <code>a</code> is greater than <code>A</code>, and
     * <code>a</code> is greater than <code>B</code>. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. </li> <li>
     * <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     * for example, equals, greater than, less than, and so on. <p>For
     * <i>KeyConditions</i>, only the following comparison operators are
     * supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     * BETWEEN</code> <p>The following are descriptions of these comparison
     * operators. <ul> <li> <p><code>EQ</code> : Equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one specified in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     * <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     * can contain only one <i>AttributeValue</i> element of type String,
     * Number, or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GE</code> : Greater than or equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> element of type String, Number, or Binary (not a
     * set type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     * contain only one <i>AttributeValue</i> element of type String, Number,
     * or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     * a prefix. <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String or Binary (not a Number or a set
     * type). The target attribute of the comparison must be of type String
     * or Binary (not a Number or a set type). <p/> </li> <li>
     * <p><code>BETWEEN</code> : Greater than or equal to the first value,
     * and less than or equal to the second value.
     * <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     * elements of the same type, either String, Number, or Binary (not a set
     * type). A target attribute matches if the target value is greater than,
     * or equal to, the first element and less than, or equal to, the second
     * element. If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not compare to
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     * usage examples of <i>AttributeValueList</i> and
     * <i>ComparisonOperator</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     * Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     * Guide</i>.
     *
     * @param keyConditions <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>KeyConditionExpression</i> instead. Do
     *         not combine legacy parameters and expression parameters in a single
     *         API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>The selection criteria for the query. For a
     *         query on a table, you can have conditions only on the table primary
     *         key attributes. You must provide the hash key attribute name and value
     *         as an <code>EQ</code> condition. You can optionally provide a second
     *         condition, referring to the range key attribute. <note> <p>If you
     *         don't provide a range key condition, all of the items that match the
     *         hash key will be retrieved. If a <i>FilterExpression</i> or
     *         <i>QueryFilter</i> is present, it will be applied after the items are
     *         retrieved.</note> <p>For a query on an index, you can have conditions
     *         only on the index key attributes. You must provide the index hash
     *         attribute name and value as an <code>EQ</code> condition. You can
     *         optionally provide a second condition, referring to the index key
     *         range attribute. <p>Each <i>KeyConditions</i> element consists of an
     *         attribute name to compare, along with the following: <ul> <li>
     *         <p><i>AttributeValueList</i> - One or more values to evaluate against
     *         the supplied attribute. The number of values in the list depends on
     *         the <i>ComparisonOperator</i> being used. <p>For type Number, value
     *         comparisons are numeric. <p>String value comparisons for greater than,
     *         equals, or less than are based on ASCII character code values. For
     *         example, <code>a</code> is greater than <code>A</code>, and
     *         <code>a</code> is greater than <code>B</code>. For a list of code
     *         values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *         <p>For Binary, DynamoDB treats each byte of the binary data as
     *         unsigned when it compares binary values. </li> <li>
     *         <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     *         for example, equals, greater than, less than, and so on. <p>For
     *         <i>KeyConditions</i>, only the following comparison operators are
     *         supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     *         BETWEEN</code> <p>The following are descriptions of these comparison
     *         operators. <ul> <li> <p><code>EQ</code> : Equal.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String, Number, or Binary (not a set
     *         type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one specified in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     *         <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     *         can contain only one <i>AttributeValue</i> element of type String,
     *         Number, or Binary (not a set type). If an item contains an
     *         <i>AttributeValue</i> element of a different type than the one
     *         provided in the request, the value does not match. For example,
     *         <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     *         <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     *         "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String, Number, or Binary (not a set
     *         type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>GE</code> : Greater than or equal.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> element of type String, Number, or Binary (not a
     *         set type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     *         contain only one <i>AttributeValue</i> element of type String, Number,
     *         or Binary (not a set type). If an item contains an
     *         <i>AttributeValue</i> element of a different type than the one
     *         provided in the request, the value does not match. For example,
     *         <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     *         <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     *         "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     *         a prefix. <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String or Binary (not a Number or a set
     *         type). The target attribute of the comparison must be of type String
     *         or Binary (not a Number or a set type). <p/> </li> <li>
     *         <p><code>BETWEEN</code> : Greater than or equal to the first value,
     *         and less than or equal to the second value.
     *         <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     *         elements of the same type, either String, Number, or Binary (not a set
     *         type). A target attribute matches if the target value is greater than,
     *         or equal to, the first element and less than, or equal to, the second
     *         element. If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not compare to
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     *         usage examples of <i>AttributeValueList</i> and
     *         <i>ComparisonOperator</i>, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     *         Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     *         Guide</i>.
     */
    public void setKeyConditions(java.util.Map<String,Condition> keyConditions) {
        this.keyConditions = keyConditions;
    }
    
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>KeyConditionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>The selection criteria for the query. For a
     * query on a table, you can have conditions only on the table primary
     * key attributes. You must provide the hash key attribute name and value
     * as an <code>EQ</code> condition. You can optionally provide a second
     * condition, referring to the range key attribute. <note> <p>If you
     * don't provide a range key condition, all of the items that match the
     * hash key will be retrieved. If a <i>FilterExpression</i> or
     * <i>QueryFilter</i> is present, it will be applied after the items are
     * retrieved.</note> <p>For a query on an index, you can have conditions
     * only on the index key attributes. You must provide the index hash
     * attribute name and value as an <code>EQ</code> condition. You can
     * optionally provide a second condition, referring to the index key
     * range attribute. <p>Each <i>KeyConditions</i> element consists of an
     * attribute name to compare, along with the following: <ul> <li>
     * <p><i>AttributeValueList</i> - One or more values to evaluate against
     * the supplied attribute. The number of values in the list depends on
     * the <i>ComparisonOperator</i> being used. <p>For type Number, value
     * comparisons are numeric. <p>String value comparisons for greater than,
     * equals, or less than are based on ASCII character code values. For
     * example, <code>a</code> is greater than <code>A</code>, and
     * <code>a</code> is greater than <code>B</code>. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. </li> <li>
     * <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     * for example, equals, greater than, less than, and so on. <p>For
     * <i>KeyConditions</i>, only the following comparison operators are
     * supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     * BETWEEN</code> <p>The following are descriptions of these comparison
     * operators. <ul> <li> <p><code>EQ</code> : Equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one specified in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     * <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     * can contain only one <i>AttributeValue</i> element of type String,
     * Number, or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GE</code> : Greater than or equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> element of type String, Number, or Binary (not a
     * set type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     * contain only one <i>AttributeValue</i> element of type String, Number,
     * or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     * a prefix. <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String or Binary (not a Number or a set
     * type). The target attribute of the comparison must be of type String
     * or Binary (not a Number or a set type). <p/> </li> <li>
     * <p><code>BETWEEN</code> : Greater than or equal to the first value,
     * and less than or equal to the second value.
     * <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     * elements of the same type, either String, Number, or Binary (not a set
     * type). A target attribute matches if the target value is greater than,
     * or equal to, the first element and less than, or equal to, the second
     * element. If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not compare to
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     * usage examples of <i>AttributeValueList</i> and
     * <i>ComparisonOperator</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     * Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     * Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param keyConditions <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>KeyConditionExpression</i> instead. Do
     *         not combine legacy parameters and expression parameters in a single
     *         API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>The selection criteria for the query. For a
     *         query on a table, you can have conditions only on the table primary
     *         key attributes. You must provide the hash key attribute name and value
     *         as an <code>EQ</code> condition. You can optionally provide a second
     *         condition, referring to the range key attribute. <note> <p>If you
     *         don't provide a range key condition, all of the items that match the
     *         hash key will be retrieved. If a <i>FilterExpression</i> or
     *         <i>QueryFilter</i> is present, it will be applied after the items are
     *         retrieved.</note> <p>For a query on an index, you can have conditions
     *         only on the index key attributes. You must provide the index hash
     *         attribute name and value as an <code>EQ</code> condition. You can
     *         optionally provide a second condition, referring to the index key
     *         range attribute. <p>Each <i>KeyConditions</i> element consists of an
     *         attribute name to compare, along with the following: <ul> <li>
     *         <p><i>AttributeValueList</i> - One or more values to evaluate against
     *         the supplied attribute. The number of values in the list depends on
     *         the <i>ComparisonOperator</i> being used. <p>For type Number, value
     *         comparisons are numeric. <p>String value comparisons for greater than,
     *         equals, or less than are based on ASCII character code values. For
     *         example, <code>a</code> is greater than <code>A</code>, and
     *         <code>a</code> is greater than <code>B</code>. For a list of code
     *         values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *         <p>For Binary, DynamoDB treats each byte of the binary data as
     *         unsigned when it compares binary values. </li> <li>
     *         <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     *         for example, equals, greater than, less than, and so on. <p>For
     *         <i>KeyConditions</i>, only the following comparison operators are
     *         supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     *         BETWEEN</code> <p>The following are descriptions of these comparison
     *         operators. <ul> <li> <p><code>EQ</code> : Equal.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String, Number, or Binary (not a set
     *         type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one specified in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     *         <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     *         can contain only one <i>AttributeValue</i> element of type String,
     *         Number, or Binary (not a set type). If an item contains an
     *         <i>AttributeValue</i> element of a different type than the one
     *         provided in the request, the value does not match. For example,
     *         <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     *         <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     *         "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String, Number, or Binary (not a set
     *         type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>GE</code> : Greater than or equal.
     *         <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> element of type String, Number, or Binary (not a
     *         set type). If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not equal
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     *         <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     *         contain only one <i>AttributeValue</i> element of type String, Number,
     *         or Binary (not a set type). If an item contains an
     *         <i>AttributeValue</i> element of a different type than the one
     *         provided in the request, the value does not match. For example,
     *         <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     *         <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     *         "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     *         a prefix. <p><i>AttributeValueList</i> can contain only one
     *         <i>AttributeValue</i> of type String or Binary (not a Number or a set
     *         type). The target attribute of the comparison must be of type String
     *         or Binary (not a Number or a set type). <p/> </li> <li>
     *         <p><code>BETWEEN</code> : Greater than or equal to the first value,
     *         and less than or equal to the second value.
     *         <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     *         elements of the same type, either String, Number, or Binary (not a set
     *         type). A target attribute matches if the target value is greater than,
     *         or equal to, the first element and less than, or equal to, the second
     *         element. If an item contains an <i>AttributeValue</i> element of a
     *         different type than the one provided in the request, the value does
     *         not match. For example, <code>{"S":"6"}</code> does not compare to
     *         <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     *         to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     *         usage examples of <i>AttributeValueList</i> and
     *         <i>ComparisonOperator</i>, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     *         Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     *         Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withKeyConditions(java.util.Map<String,Condition> keyConditions) {
        setKeyConditions(keyConditions);
        return this;
    }

    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>KeyConditionExpression</i> instead. Do
     * not combine legacy parameters and expression parameters in a single
     * API call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>The selection criteria for the query. For a
     * query on a table, you can have conditions only on the table primary
     * key attributes. You must provide the hash key attribute name and value
     * as an <code>EQ</code> condition. You can optionally provide a second
     * condition, referring to the range key attribute. <note> <p>If you
     * don't provide a range key condition, all of the items that match the
     * hash key will be retrieved. If a <i>FilterExpression</i> or
     * <i>QueryFilter</i> is present, it will be applied after the items are
     * retrieved.</note> <p>For a query on an index, you can have conditions
     * only on the index key attributes. You must provide the index hash
     * attribute name and value as an <code>EQ</code> condition. You can
     * optionally provide a second condition, referring to the index key
     * range attribute. <p>Each <i>KeyConditions</i> element consists of an
     * attribute name to compare, along with the following: <ul> <li>
     * <p><i>AttributeValueList</i> - One or more values to evaluate against
     * the supplied attribute. The number of values in the list depends on
     * the <i>ComparisonOperator</i> being used. <p>For type Number, value
     * comparisons are numeric. <p>String value comparisons for greater than,
     * equals, or less than are based on ASCII character code values. For
     * example, <code>a</code> is greater than <code>A</code>, and
     * <code>a</code> is greater than <code>B</code>. For a list of code
     * values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. </li> <li>
     * <p><i>ComparisonOperator</i> - A comparator for evaluating attributes,
     * for example, equals, greater than, less than, and so on. <p>For
     * <i>KeyConditions</i>, only the following comparison operators are
     * supported: <p> <code>EQ | LE | LT | GE | GT | BEGINS_WITH |
     * BETWEEN</code> <p>The following are descriptions of these comparison
     * operators. <ul> <li> <p><code>EQ</code> : Equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one specified in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not equal
     * <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>LE</code> : Less than or equal. <p><i>AttributeValueList</i>
     * can contain only one <i>AttributeValue</i> element of type String,
     * Number, or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>LT</code> : Less than.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String, Number, or Binary (not a set
     * type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GE</code> : Greater than or equal.
     * <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> element of type String, Number, or Binary (not a
     * set type). If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not equal
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code>. <p/> </li> <li>
     * <p><code>GT</code> : Greater than. <p><i>AttributeValueList</i> can
     * contain only one <i>AttributeValue</i> element of type String, Number,
     * or Binary (not a set type). If an item contains an
     * <i>AttributeValue</i> element of a different type than the one
     * provided in the request, the value does not match. For example,
     * <code>{"S":"6"}</code> does not equal <code>{"N":"6"}</code>. Also,
     * <code>{"N":"6"}</code> does not compare to <code>{"NS":["6", "2",
     * "1"]}</code>. <p/> </li> <li> <p><code>BEGINS_WITH</code> : Checks for
     * a prefix. <p><i>AttributeValueList</i> can contain only one
     * <i>AttributeValue</i> of type String or Binary (not a Number or a set
     * type). The target attribute of the comparison must be of type String
     * or Binary (not a Number or a set type). <p/> </li> <li>
     * <p><code>BETWEEN</code> : Greater than or equal to the first value,
     * and less than or equal to the second value.
     * <p><i>AttributeValueList</i> must contain two <i>AttributeValue</i>
     * elements of the same type, either String, Number, or Binary (not a set
     * type). A target attribute matches if the target value is greater than,
     * or equal to, the first element and less than, or equal to, the second
     * element. If an item contains an <i>AttributeValue</i> element of a
     * different type than the one provided in the request, the value does
     * not match. For example, <code>{"S":"6"}</code> does not compare to
     * <code>{"N":"6"}</code>. Also, <code>{"N":"6"}</code> does not compare
     * to <code>{"NS":["6", "2", "1"]}</code> </li> </ul> </li> </ul> <p>For
     * usage examples of <i>AttributeValueList</i> and
     * <i>ComparisonOperator</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/LegacyConditionalParameters.html">Legacy
     * Conditional Parameters</a> in the <i>Amazon DynamoDB Developer
     * Guide</i>.
     * <p>
     * The method adds a new key-value pair into KeyConditions parameter, and
     * returns a reference to this object so that method calls can be chained
     * together.
     *
     * @param key The key of the entry to be added into KeyConditions.
     * @param value The corresponding value of the entry to be added into KeyConditions.
     */
  public QueryRequest addKeyConditionsEntry(String key, Condition value) {
    if (null == this.keyConditions) {
      this.keyConditions = new java.util.HashMap<String,Condition>();
    }
    if (this.keyConditions.containsKey(key))
      throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
    this.keyConditions.put(key, value);
    return this;
  }

  /**
   * Removes all the entries added into KeyConditions.
   * <p>
   * Returns a reference to this object so that method calls can be chained together.
   */
  public QueryRequest clearKeyConditionsEntries() {
    this.keyConditions = null;
    return this;
  }
  
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A condition that evaluates the query
     * results after the items are read and returns only the desired values.
     * <p>This parameter does not support attributes of type List or Map.
     * <note> <p>A <i>QueryFilter</i> is applied after the items have already
     * been read; the process of filtering does not consume any additional
     * read capacity units.</note> <p>If you provide more than one condition
     * in the <i>QueryFilter</i> map, then by default all of the conditions
     * must evaluate to true. In other words, the conditions are ANDed
     * together. (You can use the <i>ConditionalOperator</i> parameter to OR
     * the conditions instead. If you do this, then at least one of the
     * conditions must evaluate to true, rather than all of them.) <p>Note
     * that <i>QueryFilter</i> does not allow key attributes. You cannot
     * define a filter condition on a hash key or range key. <p>Each
     * <i>QueryFilter</i> element consists of an attribute name to compare,
     * along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     * or more values to evaluate against the supplied attribute. The number
     * of values in the list depends on the operator specified in
     * <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     * numeric. <p>String value comparisons for greater than, equals, or less
     * than are based on ASCII character code values. For example,
     * <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     * greater than <code>B</code>. For a list of code values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For type Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. <p>For information on
     * specifying data types in JSON, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     * Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     * <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     * attributes. For example, equals, greater than, less than, etc. <p>The
     * following comparison operators are available: <p><code>EQ | NE | LE |
     * LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     * | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     * operators, see the <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     * data type. </li> </ul>
     *
     * @return <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A condition that evaluates the query
     *         results after the items are read and returns only the desired values.
     *         <p>This parameter does not support attributes of type List or Map.
     *         <note> <p>A <i>QueryFilter</i> is applied after the items have already
     *         been read; the process of filtering does not consume any additional
     *         read capacity units.</note> <p>If you provide more than one condition
     *         in the <i>QueryFilter</i> map, then by default all of the conditions
     *         must evaluate to true. In other words, the conditions are ANDed
     *         together. (You can use the <i>ConditionalOperator</i> parameter to OR
     *         the conditions instead. If you do this, then at least one of the
     *         conditions must evaluate to true, rather than all of them.) <p>Note
     *         that <i>QueryFilter</i> does not allow key attributes. You cannot
     *         define a filter condition on a hash key or range key. <p>Each
     *         <i>QueryFilter</i> element consists of an attribute name to compare,
     *         along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     *         or more values to evaluate against the supplied attribute. The number
     *         of values in the list depends on the operator specified in
     *         <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     *         numeric. <p>String value comparisons for greater than, equals, or less
     *         than are based on ASCII character code values. For example,
     *         <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     *         greater than <code>B</code>. For a list of code values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *         <p>For type Binary, DynamoDB treats each byte of the binary data as
     *         unsigned when it compares binary values. <p>For information on
     *         specifying data types in JSON, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     *         Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     *         <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     *         attributes. For example, equals, greater than, less than, etc. <p>The
     *         following comparison operators are available: <p><code>EQ | NE | LE |
     *         LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     *         | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     *         operators, see the <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     *         data type. </li> </ul>
     */
    public java.util.Map<String,Condition> getQueryFilter() {
        
        return queryFilter;
    }
    
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A condition that evaluates the query
     * results after the items are read and returns only the desired values.
     * <p>This parameter does not support attributes of type List or Map.
     * <note> <p>A <i>QueryFilter</i> is applied after the items have already
     * been read; the process of filtering does not consume any additional
     * read capacity units.</note> <p>If you provide more than one condition
     * in the <i>QueryFilter</i> map, then by default all of the conditions
     * must evaluate to true. In other words, the conditions are ANDed
     * together. (You can use the <i>ConditionalOperator</i> parameter to OR
     * the conditions instead. If you do this, then at least one of the
     * conditions must evaluate to true, rather than all of them.) <p>Note
     * that <i>QueryFilter</i> does not allow key attributes. You cannot
     * define a filter condition on a hash key or range key. <p>Each
     * <i>QueryFilter</i> element consists of an attribute name to compare,
     * along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     * or more values to evaluate against the supplied attribute. The number
     * of values in the list depends on the operator specified in
     * <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     * numeric. <p>String value comparisons for greater than, equals, or less
     * than are based on ASCII character code values. For example,
     * <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     * greater than <code>B</code>. For a list of code values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For type Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. <p>For information on
     * specifying data types in JSON, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     * Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     * <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     * attributes. For example, equals, greater than, less than, etc. <p>The
     * following comparison operators are available: <p><code>EQ | NE | LE |
     * LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     * | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     * operators, see the <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     * data type. </li> </ul>
     *
     * @param queryFilter <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A condition that evaluates the query
     *         results after the items are read and returns only the desired values.
     *         <p>This parameter does not support attributes of type List or Map.
     *         <note> <p>A <i>QueryFilter</i> is applied after the items have already
     *         been read; the process of filtering does not consume any additional
     *         read capacity units.</note> <p>If you provide more than one condition
     *         in the <i>QueryFilter</i> map, then by default all of the conditions
     *         must evaluate to true. In other words, the conditions are ANDed
     *         together. (You can use the <i>ConditionalOperator</i> parameter to OR
     *         the conditions instead. If you do this, then at least one of the
     *         conditions must evaluate to true, rather than all of them.) <p>Note
     *         that <i>QueryFilter</i> does not allow key attributes. You cannot
     *         define a filter condition on a hash key or range key. <p>Each
     *         <i>QueryFilter</i> element consists of an attribute name to compare,
     *         along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     *         or more values to evaluate against the supplied attribute. The number
     *         of values in the list depends on the operator specified in
     *         <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     *         numeric. <p>String value comparisons for greater than, equals, or less
     *         than are based on ASCII character code values. For example,
     *         <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     *         greater than <code>B</code>. For a list of code values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *         <p>For type Binary, DynamoDB treats each byte of the binary data as
     *         unsigned when it compares binary values. <p>For information on
     *         specifying data types in JSON, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     *         Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     *         <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     *         attributes. For example, equals, greater than, less than, etc. <p>The
     *         following comparison operators are available: <p><code>EQ | NE | LE |
     *         LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     *         | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     *         operators, see the <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     *         data type. </li> </ul>
     */
    public void setQueryFilter(java.util.Map<String,Condition> queryFilter) {
        this.queryFilter = queryFilter;
    }
    
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A condition that evaluates the query
     * results after the items are read and returns only the desired values.
     * <p>This parameter does not support attributes of type List or Map.
     * <note> <p>A <i>QueryFilter</i> is applied after the items have already
     * been read; the process of filtering does not consume any additional
     * read capacity units.</note> <p>If you provide more than one condition
     * in the <i>QueryFilter</i> map, then by default all of the conditions
     * must evaluate to true. In other words, the conditions are ANDed
     * together. (You can use the <i>ConditionalOperator</i> parameter to OR
     * the conditions instead. If you do this, then at least one of the
     * conditions must evaluate to true, rather than all of them.) <p>Note
     * that <i>QueryFilter</i> does not allow key attributes. You cannot
     * define a filter condition on a hash key or range key. <p>Each
     * <i>QueryFilter</i> element consists of an attribute name to compare,
     * along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     * or more values to evaluate against the supplied attribute. The number
     * of values in the list depends on the operator specified in
     * <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     * numeric. <p>String value comparisons for greater than, equals, or less
     * than are based on ASCII character code values. For example,
     * <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     * greater than <code>B</code>. For a list of code values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For type Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. <p>For information on
     * specifying data types in JSON, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     * Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     * <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     * attributes. For example, equals, greater than, less than, etc. <p>The
     * following comparison operators are available: <p><code>EQ | NE | LE |
     * LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     * | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     * operators, see the <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     * data type. </li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queryFilter <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A condition that evaluates the query
     *         results after the items are read and returns only the desired values.
     *         <p>This parameter does not support attributes of type List or Map.
     *         <note> <p>A <i>QueryFilter</i> is applied after the items have already
     *         been read; the process of filtering does not consume any additional
     *         read capacity units.</note> <p>If you provide more than one condition
     *         in the <i>QueryFilter</i> map, then by default all of the conditions
     *         must evaluate to true. In other words, the conditions are ANDed
     *         together. (You can use the <i>ConditionalOperator</i> parameter to OR
     *         the conditions instead. If you do this, then at least one of the
     *         conditions must evaluate to true, rather than all of them.) <p>Note
     *         that <i>QueryFilter</i> does not allow key attributes. You cannot
     *         define a filter condition on a hash key or range key. <p>Each
     *         <i>QueryFilter</i> element consists of an attribute name to compare,
     *         along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     *         or more values to evaluate against the supplied attribute. The number
     *         of values in the list depends on the operator specified in
     *         <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     *         numeric. <p>String value comparisons for greater than, equals, or less
     *         than are based on ASCII character code values. For example,
     *         <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     *         greater than <code>B</code>. For a list of code values, see <a
     *         href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     *         <p>For type Binary, DynamoDB treats each byte of the binary data as
     *         unsigned when it compares binary values. <p>For information on
     *         specifying data types in JSON, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     *         Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     *         <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     *         attributes. For example, equals, greater than, less than, etc. <p>The
     *         following comparison operators are available: <p><code>EQ | NE | LE |
     *         LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     *         | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     *         operators, see the <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     *         data type. </li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withQueryFilter(java.util.Map<String,Condition> queryFilter) {
        setQueryFilter(queryFilter);
        return this;
    }

    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A condition that evaluates the query
     * results after the items are read and returns only the desired values.
     * <p>This parameter does not support attributes of type List or Map.
     * <note> <p>A <i>QueryFilter</i> is applied after the items have already
     * been read; the process of filtering does not consume any additional
     * read capacity units.</note> <p>If you provide more than one condition
     * in the <i>QueryFilter</i> map, then by default all of the conditions
     * must evaluate to true. In other words, the conditions are ANDed
     * together. (You can use the <i>ConditionalOperator</i> parameter to OR
     * the conditions instead. If you do this, then at least one of the
     * conditions must evaluate to true, rather than all of them.) <p>Note
     * that <i>QueryFilter</i> does not allow key attributes. You cannot
     * define a filter condition on a hash key or range key. <p>Each
     * <i>QueryFilter</i> element consists of an attribute name to compare,
     * along with the following: <ul> <li> <p><i>AttributeValueList</i> - One
     * or more values to evaluate against the supplied attribute. The number
     * of values in the list depends on the operator specified in
     * <i>ComparisonOperator</i>. <p>For type Number, value comparisons are
     * numeric. <p>String value comparisons for greater than, equals, or less
     * than are based on ASCII character code values. For example,
     * <code>a</code> is greater than <code>A</code>, and <code>a</code> is
     * greater than <code>B</code>. For a list of code values, see <a
     * href="http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters">http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters</a>.
     * <p>For type Binary, DynamoDB treats each byte of the binary data as
     * unsigned when it compares binary values. <p>For information on
     * specifying data types in JSON, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataFormat.html">JSON
     * Data Format</a> in the <i>Amazon DynamoDB Developer Guide</i>. </li>
     * <li><p><i>ComparisonOperator</i> - A comparator for evaluating
     * attributes. For example, equals, greater than, less than, etc. <p>The
     * following comparison operators are available: <p><code>EQ | NE | LE |
     * LT | GE | GT | NOT_NULL | NULL | CONTAINS | NOT_CONTAINS | BEGINS_WITH
     * | IN | BETWEEN</code> <p>For complete descriptions of all comparison
     * operators, see the <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/APIReference/API_Condition.html">Condition</a>
     * data type. </li> </ul>
     * <p>
     * The method adds a new key-value pair into QueryFilter parameter, and
     * returns a reference to this object so that method calls can be chained
     * together.
     *
     * @param key The key of the entry to be added into QueryFilter.
     * @param value The corresponding value of the entry to be added into QueryFilter.
     */
  public QueryRequest addQueryFilterEntry(String key, Condition value) {
    if (null == this.queryFilter) {
      this.queryFilter = new java.util.HashMap<String,Condition>();
    }
    if (this.queryFilter.containsKey(key))
      throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
    this.queryFilter.put(key, value);
    return this;
  }

  /**
   * Removes all the entries added into QueryFilter.
   * <p>
   * Returns a reference to this object so that method calls can be chained together.
   */
  public QueryRequest clearQueryFilterEntries() {
    this.queryFilter = null;
    return this;
  }
  
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A logical operator to apply to the
     * conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     * If all of the conditions evaluate to true, then the entire map
     * evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     * the conditions evaluate to true, then the entire map evaluates to
     * true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     * <code>AND</code> is the default. <p>The operation will succeed only if
     * the entire map evaluates to true. <note><p>This parameter does not
     * support attributes of type List or Map.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>AND, OR
     *
     * @return <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A logical operator to apply to the
     *         conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     *         If all of the conditions evaluate to true, then the entire map
     *         evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     *         the conditions evaluate to true, then the entire map evaluates to
     *         true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     *         <code>AND</code> is the default. <p>The operation will succeed only if
     *         the entire map evaluates to true. <note><p>This parameter does not
     *         support attributes of type List or Map.</note>
     *
     * @see ConditionalOperator
     */
    public String getConditionalOperator() {
        return conditionalOperator;
    }
    
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A logical operator to apply to the
     * conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     * If all of the conditions evaluate to true, then the entire map
     * evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     * the conditions evaluate to true, then the entire map evaluates to
     * true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     * <code>AND</code> is the default. <p>The operation will succeed only if
     * the entire map evaluates to true. <note><p>This parameter does not
     * support attributes of type List or Map.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>AND, OR
     *
     * @param conditionalOperator <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A logical operator to apply to the
     *         conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     *         If all of the conditions evaluate to true, then the entire map
     *         evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     *         the conditions evaluate to true, then the entire map evaluates to
     *         true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     *         <code>AND</code> is the default. <p>The operation will succeed only if
     *         the entire map evaluates to true. <note><p>This parameter does not
     *         support attributes of type List or Map.</note>
     *
     * @see ConditionalOperator
     */
    public void setConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
    }
    
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A logical operator to apply to the
     * conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     * If all of the conditions evaluate to true, then the entire map
     * evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     * the conditions evaluate to true, then the entire map evaluates to
     * true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     * <code>AND</code> is the default. <p>The operation will succeed only if
     * the entire map evaluates to true. <note><p>This parameter does not
     * support attributes of type List or Map.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>AND, OR
     *
     * @param conditionalOperator <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A logical operator to apply to the
     *         conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     *         If all of the conditions evaluate to true, then the entire map
     *         evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     *         the conditions evaluate to true, then the entire map evaluates to
     *         true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     *         <code>AND</code> is the default. <p>The operation will succeed only if
     *         the entire map evaluates to true. <note><p>This parameter does not
     *         support attributes of type List or Map.</note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ConditionalOperator
     */
    public QueryRequest withConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
        return this;
    }

    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A logical operator to apply to the
     * conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     * If all of the conditions evaluate to true, then the entire map
     * evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     * the conditions evaluate to true, then the entire map evaluates to
     * true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     * <code>AND</code> is the default. <p>The operation will succeed only if
     * the entire map evaluates to true. <note><p>This parameter does not
     * support attributes of type List or Map.</note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>AND, OR
     *
     * @param conditionalOperator <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A logical operator to apply to the
     *         conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     *         If all of the conditions evaluate to true, then the entire map
     *         evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     *         the conditions evaluate to true, then the entire map evaluates to
     *         true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     *         <code>AND</code> is the default. <p>The operation will succeed only if
     *         the entire map evaluates to true. <note><p>This parameter does not
     *         support attributes of type List or Map.</note>
     *
     * @see ConditionalOperator
     */
    public void setConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
    }
    
    /**
     * <important> <p>This is a legacy parameter, for backward compatibility.
     * New applications should use <i>FilterExpression</i> instead. Do not
     * combine legacy parameters and expression parameters in a single API
     * call; otherwise, DynamoDB will return a <i>ValidationException</i>
     * exception. </important> <p>A logical operator to apply to the
     * conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     * If all of the conditions evaluate to true, then the entire map
     * evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     * the conditions evaluate to true, then the entire map evaluates to
     * true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     * <code>AND</code> is the default. <p>The operation will succeed only if
     * the entire map evaluates to true. <note><p>This parameter does not
     * support attributes of type List or Map.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>AND, OR
     *
     * @param conditionalOperator <important> <p>This is a legacy parameter, for backward compatibility.
     *         New applications should use <i>FilterExpression</i> instead. Do not
     *         combine legacy parameters and expression parameters in a single API
     *         call; otherwise, DynamoDB will return a <i>ValidationException</i>
     *         exception. </important> <p>A logical operator to apply to the
     *         conditions in a <i>QueryFilter</i> map: <ul> <li><p><code>AND</code> -
     *         If all of the conditions evaluate to true, then the entire map
     *         evaluates to true.</li> <li><p><code>OR</code> - If at least one of
     *         the conditions evaluate to true, then the entire map evaluates to
     *         true.</li> </ul> <p>If you omit <i>ConditionalOperator</i>, then
     *         <code>AND</code> is the default. <p>The operation will succeed only if
     *         the entire map evaluates to true. <note><p>This parameter does not
     *         support attributes of type List or Map.</note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ConditionalOperator
     */
    public QueryRequest withConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
        return this;
    }

    /**
     * A value that specifies ascending (true) or descending (false)
     * traversal of the index. DynamoDB returns results reflecting the
     * requested order determined by the range key. If the data type is
     * Number, the results are returned in numeric order. For type String,
     * the results are returned in order of ASCII character code values. For
     * type Binary, DynamoDB treats each byte of the binary data as unsigned
     * when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     * specified, the results are returned in ascending order.
     *
     * @return A value that specifies ascending (true) or descending (false)
     *         traversal of the index. DynamoDB returns results reflecting the
     *         requested order determined by the range key. If the data type is
     *         Number, the results are returned in numeric order. For type String,
     *         the results are returned in order of ASCII character code values. For
     *         type Binary, DynamoDB treats each byte of the binary data as unsigned
     *         when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     *         specified, the results are returned in ascending order.
     */
    public Boolean isScanIndexForward() {
        return scanIndexForward;
    }
    
    /**
     * A value that specifies ascending (true) or descending (false)
     * traversal of the index. DynamoDB returns results reflecting the
     * requested order determined by the range key. If the data type is
     * Number, the results are returned in numeric order. For type String,
     * the results are returned in order of ASCII character code values. For
     * type Binary, DynamoDB treats each byte of the binary data as unsigned
     * when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     * specified, the results are returned in ascending order.
     *
     * @param scanIndexForward A value that specifies ascending (true) or descending (false)
     *         traversal of the index. DynamoDB returns results reflecting the
     *         requested order determined by the range key. If the data type is
     *         Number, the results are returned in numeric order. For type String,
     *         the results are returned in order of ASCII character code values. For
     *         type Binary, DynamoDB treats each byte of the binary data as unsigned
     *         when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     *         specified, the results are returned in ascending order.
     */
    public void setScanIndexForward(Boolean scanIndexForward) {
        this.scanIndexForward = scanIndexForward;
    }
    
    /**
     * A value that specifies ascending (true) or descending (false)
     * traversal of the index. DynamoDB returns results reflecting the
     * requested order determined by the range key. If the data type is
     * Number, the results are returned in numeric order. For type String,
     * the results are returned in order of ASCII character code values. For
     * type Binary, DynamoDB treats each byte of the binary data as unsigned
     * when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     * specified, the results are returned in ascending order.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param scanIndexForward A value that specifies ascending (true) or descending (false)
     *         traversal of the index. DynamoDB returns results reflecting the
     *         requested order determined by the range key. If the data type is
     *         Number, the results are returned in numeric order. For type String,
     *         the results are returned in order of ASCII character code values. For
     *         type Binary, DynamoDB treats each byte of the binary data as unsigned
     *         when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     *         specified, the results are returned in ascending order.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withScanIndexForward(Boolean scanIndexForward) {
        this.scanIndexForward = scanIndexForward;
        return this;
    }

    /**
     * A value that specifies ascending (true) or descending (false)
     * traversal of the index. DynamoDB returns results reflecting the
     * requested order determined by the range key. If the data type is
     * Number, the results are returned in numeric order. For type String,
     * the results are returned in order of ASCII character code values. For
     * type Binary, DynamoDB treats each byte of the binary data as unsigned
     * when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     * specified, the results are returned in ascending order.
     *
     * @return A value that specifies ascending (true) or descending (false)
     *         traversal of the index. DynamoDB returns results reflecting the
     *         requested order determined by the range key. If the data type is
     *         Number, the results are returned in numeric order. For type String,
     *         the results are returned in order of ASCII character code values. For
     *         type Binary, DynamoDB treats each byte of the binary data as unsigned
     *         when it compares binary values. <p>If <i>ScanIndexForward</i> is not
     *         specified, the results are returned in ascending order.
     */
    public Boolean getScanIndexForward() {
        return scanIndexForward;
    }

    /**
     * The primary key of the first item that this operation will evaluate.
     * Use the value that was returned for <i>LastEvaluatedKey</i> in the
     * previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     * be String, Number or Binary. No set data types are allowed.
     *
     * @return The primary key of the first item that this operation will evaluate.
     *         Use the value that was returned for <i>LastEvaluatedKey</i> in the
     *         previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     *         be String, Number or Binary. No set data types are allowed.
     */
    public java.util.Map<String,AttributeValue> getExclusiveStartKey() {
        
        return exclusiveStartKey;
    }
    
    /**
     * The primary key of the first item that this operation will evaluate.
     * Use the value that was returned for <i>LastEvaluatedKey</i> in the
     * previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     * be String, Number or Binary. No set data types are allowed.
     *
     * @param exclusiveStartKey The primary key of the first item that this operation will evaluate.
     *         Use the value that was returned for <i>LastEvaluatedKey</i> in the
     *         previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     *         be String, Number or Binary. No set data types are allowed.
     */
    public void setExclusiveStartKey(java.util.Map<String,AttributeValue> exclusiveStartKey) {
        this.exclusiveStartKey = exclusiveStartKey;
    }
    
    /**
     * The primary key of the first item that this operation will evaluate.
     * Use the value that was returned for <i>LastEvaluatedKey</i> in the
     * previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     * be String, Number or Binary. No set data types are allowed.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param exclusiveStartKey The primary key of the first item that this operation will evaluate.
     *         Use the value that was returned for <i>LastEvaluatedKey</i> in the
     *         previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     *         be String, Number or Binary. No set data types are allowed.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withExclusiveStartKey(java.util.Map<String,AttributeValue> exclusiveStartKey) {
        setExclusiveStartKey(exclusiveStartKey);
        return this;
    }

    /**
     * The primary key of the first item that this operation will evaluate.
     * Use the value that was returned for <i>LastEvaluatedKey</i> in the
     * previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     * be String, Number or Binary. No set data types are allowed.
     * <p>
     * This method accepts the hashKey, rangeKey of ExclusiveStartKey as
     * java.util.Map.Entry<String, AttributeValue> objects.
     *
     * @param hashKey Primary hash key.
     * @param rangeKey Primary range key. (null if it a hash-only table)
     */
    public void setExclusiveStartKey(java.util.Map.Entry<String, AttributeValue> hashKey, java.util.Map.Entry<String, AttributeValue> rangeKey) throws IllegalArgumentException {
        java.util.HashMap<String,AttributeValue> exclusiveStartKey = new java.util.HashMap<String,AttributeValue>();
      
      if (hashKey != null) {
          exclusiveStartKey.put(hashKey.getKey(), hashKey.getValue());
      } else
            throw new IllegalArgumentException("hashKey must be non-null object.");
      if (rangeKey != null) {
          exclusiveStartKey.put(rangeKey.getKey(), rangeKey.getValue());
      } 
        setExclusiveStartKey(exclusiveStartKey);
    }
    
    /**
     * The primary key of the first item that this operation will evaluate.
     * Use the value that was returned for <i>LastEvaluatedKey</i> in the
     * previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     * be String, Number or Binary. No set data types are allowed.
     * <p>
     * This method accepts the hashKey, rangeKey of ExclusiveStartKey as
     * java.util.Map.Entry<String, AttributeValue> objects.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param hashKey Primary hash key.
     * @param rangeKey Primary range key. (null if it a hash-only table)
     */
    public QueryRequest withExclusiveStartKey(java.util.Map.Entry<String, AttributeValue> hashKey, java.util.Map.Entry<String, AttributeValue> rangeKey) throws IllegalArgumentException {
      setExclusiveStartKey(hashKey, rangeKey);
      return this;
    }

    /**
     * The primary key of the first item that this operation will evaluate.
     * Use the value that was returned for <i>LastEvaluatedKey</i> in the
     * previous operation. <p>The data type for <i>ExclusiveStartKey</i> must
     * be String, Number or Binary. No set data types are allowed.
     * <p>
     * The method adds a new key-value pair into ExclusiveStartKey parameter,
     * and returns a reference to this object so that method calls can be
     * chained together.
     *
     * @param key The key of the entry to be added into ExclusiveStartKey.
     * @param value The corresponding value of the entry to be added into ExclusiveStartKey.
     */
  public QueryRequest addExclusiveStartKeyEntry(String key, AttributeValue value) {
    if (null == this.exclusiveStartKey) {
      this.exclusiveStartKey = new java.util.HashMap<String,AttributeValue>();
    }
    if (this.exclusiveStartKey.containsKey(key))
      throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
    this.exclusiveStartKey.put(key, value);
    return this;
  }

  /**
   * Removes all the entries added into ExclusiveStartKey.
   * <p>
   * Returns a reference to this object so that method calls can be chained together.
   */
  public QueryRequest clearExclusiveStartKeyEntries() {
    this.exclusiveStartKey = null;
    return this;
  }
  
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @return A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @see ReturnConsumedCapacity
     */
    public String getReturnConsumedCapacity() {
        return returnConsumedCapacity;
    }
    
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @see ReturnConsumedCapacity
     */
    public void setReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
    }
    
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ReturnConsumedCapacity
     */
    public QueryRequest withReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
        return this;
    }

    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @see ReturnConsumedCapacity
     */
    public void setReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
    }
    
    /**
     * A value that if set to <code>TOTAL</code>, the response includes
     * <i>ConsumedCapacity</i> data for tables and indexes. If set to
     * <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     * for indexes. If set to <code>NONE</code> (the default),
     * <i>ConsumedCapacity</i> is not included in the response.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>INDEXES, TOTAL, NONE
     *
     * @param returnConsumedCapacity A value that if set to <code>TOTAL</code>, the response includes
     *         <i>ConsumedCapacity</i> data for tables and indexes. If set to
     *         <code>INDEXES</code>, the response includes <i>ConsumedCapacity</i>
     *         for indexes. If set to <code>NONE</code> (the default),
     *         <i>ConsumedCapacity</i> is not included in the response.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see ReturnConsumedCapacity
     */
    public QueryRequest withReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
        return this;
    }

    /**
     * A string that identifies one or more attributes to retrieve from the
     * table. These attributes can include scalars, sets, or elements of a
     * JSON document. The attributes in the expression must be separated by
     * commas. <p>If no attribute names are specified, then all attributes
     * will be returned. If any of the requested attributes are not found,
     * they will not appear in the result. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.AccessingItemAttributes.html">Accessing
     * Item Attributes</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>ProjectionExpression</i> replaces the legacy
     * <i>AttributesToGet</i> parameter.</note>
     *
     * @return A string that identifies one or more attributes to retrieve from the
     *         table. These attributes can include scalars, sets, or elements of a
     *         JSON document. The attributes in the expression must be separated by
     *         commas. <p>If no attribute names are specified, then all attributes
     *         will be returned. If any of the requested attributes are not found,
     *         they will not appear in the result. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.AccessingItemAttributes.html">Accessing
     *         Item Attributes</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     *         <note><p><i>ProjectionExpression</i> replaces the legacy
     *         <i>AttributesToGet</i> parameter.</note>
     */
    public String getProjectionExpression() {
        return projectionExpression;
    }
    
    /**
     * A string that identifies one or more attributes to retrieve from the
     * table. These attributes can include scalars, sets, or elements of a
     * JSON document. The attributes in the expression must be separated by
     * commas. <p>If no attribute names are specified, then all attributes
     * will be returned. If any of the requested attributes are not found,
     * they will not appear in the result. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.AccessingItemAttributes.html">Accessing
     * Item Attributes</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>ProjectionExpression</i> replaces the legacy
     * <i>AttributesToGet</i> parameter.</note>
     *
     * @param projectionExpression A string that identifies one or more attributes to retrieve from the
     *         table. These attributes can include scalars, sets, or elements of a
     *         JSON document. The attributes in the expression must be separated by
     *         commas. <p>If no attribute names are specified, then all attributes
     *         will be returned. If any of the requested attributes are not found,
     *         they will not appear in the result. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.AccessingItemAttributes.html">Accessing
     *         Item Attributes</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     *         <note><p><i>ProjectionExpression</i> replaces the legacy
     *         <i>AttributesToGet</i> parameter.</note>
     */
    public void setProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
    }
    
    /**
     * A string that identifies one or more attributes to retrieve from the
     * table. These attributes can include scalars, sets, or elements of a
     * JSON document. The attributes in the expression must be separated by
     * commas. <p>If no attribute names are specified, then all attributes
     * will be returned. If any of the requested attributes are not found,
     * they will not appear in the result. <p>For more information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.AccessingItemAttributes.html">Accessing
     * Item Attributes</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>ProjectionExpression</i> replaces the legacy
     * <i>AttributesToGet</i> parameter.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param projectionExpression A string that identifies one or more attributes to retrieve from the
     *         table. These attributes can include scalars, sets, or elements of a
     *         JSON document. The attributes in the expression must be separated by
     *         commas. <p>If no attribute names are specified, then all attributes
     *         will be returned. If any of the requested attributes are not found,
     *         they will not appear in the result. <p>For more information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.AccessingItemAttributes.html">Accessing
     *         Item Attributes</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     *         <note><p><i>ProjectionExpression</i> replaces the legacy
     *         <i>AttributesToGet</i> parameter.</note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
        return this;
    }

    /**
     * A string that contains conditions that DynamoDB applies after the
     * <i>Query</i> operation, but before the data is returned to you. Items
     * that do not satisfy the <i>FilterExpression</i> criteria are not
     * returned. <note> <p>A <i>FilterExpression</i> is applied after the
     * items have already been read; the process of filtering does not
     * consume any additional read capacity units. </note> <p>For more
     * information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html#FilteringResults">Filter
     * Expressions</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>FilterExpression</i> replaces the legacy
     * <i>QueryFilter</i> and <i>ConditionalOperator</i> parameters.</note>
     *
     * @return A string that contains conditions that DynamoDB applies after the
     *         <i>Query</i> operation, but before the data is returned to you. Items
     *         that do not satisfy the <i>FilterExpression</i> criteria are not
     *         returned. <note> <p>A <i>FilterExpression</i> is applied after the
     *         items have already been read; the process of filtering does not
     *         consume any additional read capacity units. </note> <p>For more
     *         information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html#FilteringResults">Filter
     *         Expressions</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     *         <note><p><i>FilterExpression</i> replaces the legacy
     *         <i>QueryFilter</i> and <i>ConditionalOperator</i> parameters.</note>
     */
    public String getFilterExpression() {
        return filterExpression;
    }
    
    /**
     * A string that contains conditions that DynamoDB applies after the
     * <i>Query</i> operation, but before the data is returned to you. Items
     * that do not satisfy the <i>FilterExpression</i> criteria are not
     * returned. <note> <p>A <i>FilterExpression</i> is applied after the
     * items have already been read; the process of filtering does not
     * consume any additional read capacity units. </note> <p>For more
     * information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html#FilteringResults">Filter
     * Expressions</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>FilterExpression</i> replaces the legacy
     * <i>QueryFilter</i> and <i>ConditionalOperator</i> parameters.</note>
     *
     * @param filterExpression A string that contains conditions that DynamoDB applies after the
     *         <i>Query</i> operation, but before the data is returned to you. Items
     *         that do not satisfy the <i>FilterExpression</i> criteria are not
     *         returned. <note> <p>A <i>FilterExpression</i> is applied after the
     *         items have already been read; the process of filtering does not
     *         consume any additional read capacity units. </note> <p>For more
     *         information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html#FilteringResults">Filter
     *         Expressions</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     *         <note><p><i>FilterExpression</i> replaces the legacy
     *         <i>QueryFilter</i> and <i>ConditionalOperator</i> parameters.</note>
     */
    public void setFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
    }
    
    /**
     * A string that contains conditions that DynamoDB applies after the
     * <i>Query</i> operation, but before the data is returned to you. Items
     * that do not satisfy the <i>FilterExpression</i> criteria are not
     * returned. <note> <p>A <i>FilterExpression</i> is applied after the
     * items have already been read; the process of filtering does not
     * consume any additional read capacity units. </note> <p>For more
     * information, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html#FilteringResults">Filter
     * Expressions</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     * <note><p><i>FilterExpression</i> replaces the legacy
     * <i>QueryFilter</i> and <i>ConditionalOperator</i> parameters.</note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param filterExpression A string that contains conditions that DynamoDB applies after the
     *         <i>Query</i> operation, but before the data is returned to you. Items
     *         that do not satisfy the <i>FilterExpression</i> criteria are not
     *         returned. <note> <p>A <i>FilterExpression</i> is applied after the
     *         items have already been read; the process of filtering does not
     *         consume any additional read capacity units. </note> <p>For more
     *         information, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/QueryAndScan.html#FilteringResults">Filter
     *         Expressions</a> in the <i>Amazon DynamoDB Developer Guide</i>.
     *         <note><p><i>FilterExpression</i> replaces the legacy
     *         <i>QueryFilter</i> and <i>ConditionalOperator</i> parameters.</note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
        return this;
    }

    /**
     * The condition that specifies the key value(s) for items to be
     * retrieved by the <i>Query</i> action. <p>The condition must perform an
     * equality test on a single hash key value. The condition can also
     * perform one of several comparison tests on a single range key value.
     * <i>Query</i> can use <i>KeyConditionExpression</i> to retrieve one
     * item with a given hash and range key value, or several items that have
     * the same hash key value but different range key values. <p>The hash
     * key equality test is required, and must be specified in the following
     * format: <p> <code>hashAttributeName</code> <i>=</i>
     * <code>:hashval</code> <p>If you also want to provide a range key
     * condition, it must be combined using <i>AND</i> with the hash key
     * condition. Following is an example, using the <b>=</b> comparison
     * operator for the range key: <p> <code>hashAttributeName</code>
     * <i>=</i> <code>:hashval</code> <i>AND</i>
     * <code>rangeAttributeName</code> <i>=</i> <code>:rangeval</code>
     * <p>Valid comparisons for the range key condition are as follows: <ul>
     * <li> <p><code>rangeAttributeName</code> <i>=</i>
     * <code>:rangeval</code> - true if the range key is equal to
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><</i> <code>:rangeval</code> - true if the range key is less than
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><=</i> <code>:rangeval</code> - true if the range key is less than
     * or equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>></i> <code>:rangeval</code> -
     * true if the range key is greater than <code>:rangeval</code>. </li>
     * <li> <p><code>rangeAttributeName</code> <i>>=
     * </i><code>:rangeval</code> - true if the range key is greater than or
     * equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>BETWEEN</i>
     * <code>:rangeval1</code> <i>AND</i> <code>:rangeval2</code> - true if
     * the range key is greater than or equal to <code>:rangeval1</code>, and
     * less than or equal to <code>:rangeval2</code>. </li> <li>
     * <p><i>begins_with (</i><code>rangeAttributeName</code>,
     * <code>:rangeval</code><i>)</i> - true if the range key begins with a
     * particular operand. (You cannot use this function with a range key
     * that is of type Number.) Note that the function name
     * <code>begins_with</code> is case-sensitive. </li> </ul> <p>Use the
     * <i>ExpressionAttributeValues</i> parameter to replace tokens such as
     * <code>:hashval</code> and <code>:rangeval</code> with actual values at
     * runtime. <p>You can optionally use the <i>ExpressionAttributeNames</i>
     * parameter to replace the names of the hash and range attributes with
     * placeholder tokens. This option might be necessary if an attribute
     * name conflicts with a DynamoDB reserved word. For example, the
     * following <i>KeyConditionExpression</i> parameter causes an error
     * because <i>Size</i> is a reserved word: <ul> <li> <code>Size =
     * :myval</code> </li> </ul> <p>To work around this, define a placeholder
     * (such a <code>#S</code>) to represent the attribute name <i>Size</i>.
     * <i>KeyConditionExpression</i> then is as follows: <ul> <li> <code>#S =
     * :myval</code> </li> </ul> <p>For a list of reserved words, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>. <p>For more
     * information on <i>ExpressionAttributeNames</i> and
     * <i>ExpressionAttributeValues</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>. <note> <p><i>KeyConditionExpression</i>
     * replaces the legacy <i>KeyConditions</i> parameter. </note>
     *
     * @return The condition that specifies the key value(s) for items to be
     *         retrieved by the <i>Query</i> action. <p>The condition must perform an
     *         equality test on a single hash key value. The condition can also
     *         perform one of several comparison tests on a single range key value.
     *         <i>Query</i> can use <i>KeyConditionExpression</i> to retrieve one
     *         item with a given hash and range key value, or several items that have
     *         the same hash key value but different range key values. <p>The hash
     *         key equality test is required, and must be specified in the following
     *         format: <p> <code>hashAttributeName</code> <i>=</i>
     *         <code>:hashval</code> <p>If you also want to provide a range key
     *         condition, it must be combined using <i>AND</i> with the hash key
     *         condition. Following is an example, using the <b>=</b> comparison
     *         operator for the range key: <p> <code>hashAttributeName</code>
     *         <i>=</i> <code>:hashval</code> <i>AND</i>
     *         <code>rangeAttributeName</code> <i>=</i> <code>:rangeval</code>
     *         <p>Valid comparisons for the range key condition are as follows: <ul>
     *         <li> <p><code>rangeAttributeName</code> <i>=</i>
     *         <code>:rangeval</code> - true if the range key is equal to
     *         <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     *         <i><</i> <code>:rangeval</code> - true if the range key is less than
     *         <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     *         <i><=</i> <code>:rangeval</code> - true if the range key is less than
     *         or equal to <code>:rangeval</code>. </li> <li>
     *         <p><code>rangeAttributeName</code> <i>></i> <code>:rangeval</code> -
     *         true if the range key is greater than <code>:rangeval</code>. </li>
     *         <li> <p><code>rangeAttributeName</code> <i>>=
     *         </i><code>:rangeval</code> - true if the range key is greater than or
     *         equal to <code>:rangeval</code>. </li> <li>
     *         <p><code>rangeAttributeName</code> <i>BETWEEN</i>
     *         <code>:rangeval1</code> <i>AND</i> <code>:rangeval2</code> - true if
     *         the range key is greater than or equal to <code>:rangeval1</code>, and
     *         less than or equal to <code>:rangeval2</code>. </li> <li>
     *         <p><i>begins_with (</i><code>rangeAttributeName</code>,
     *         <code>:rangeval</code><i>)</i> - true if the range key begins with a
     *         particular operand. (You cannot use this function with a range key
     *         that is of type Number.) Note that the function name
     *         <code>begins_with</code> is case-sensitive. </li> </ul> <p>Use the
     *         <i>ExpressionAttributeValues</i> parameter to replace tokens such as
     *         <code>:hashval</code> and <code>:rangeval</code> with actual values at
     *         runtime. <p>You can optionally use the <i>ExpressionAttributeNames</i>
     *         parameter to replace the names of the hash and range attributes with
     *         placeholder tokens. This option might be necessary if an attribute
     *         name conflicts with a DynamoDB reserved word. For example, the
     *         following <i>KeyConditionExpression</i> parameter causes an error
     *         because <i>Size</i> is a reserved word: <ul> <li> <code>Size =
     *         :myval</code> </li> </ul> <p>To work around this, define a placeholder
     *         (such a <code>#S</code>) to represent the attribute name <i>Size</i>.
     *         <i>KeyConditionExpression</i> then is as follows: <ul> <li> <code>#S =
     *         :myval</code> </li> </ul> <p>For a list of reserved words, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     *         Words</a> in the <i>Amazon DynamoDB Developer Guide</i>. <p>For more
     *         information on <i>ExpressionAttributeNames</i> and
     *         <i>ExpressionAttributeValues</i>, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>. <note> <p><i>KeyConditionExpression</i>
     *         replaces the legacy <i>KeyConditions</i> parameter. </note>
     */
    public String getKeyConditionExpression() {
        return keyConditionExpression;
    }
    
    /**
     * The condition that specifies the key value(s) for items to be
     * retrieved by the <i>Query</i> action. <p>The condition must perform an
     * equality test on a single hash key value. The condition can also
     * perform one of several comparison tests on a single range key value.
     * <i>Query</i> can use <i>KeyConditionExpression</i> to retrieve one
     * item with a given hash and range key value, or several items that have
     * the same hash key value but different range key values. <p>The hash
     * key equality test is required, and must be specified in the following
     * format: <p> <code>hashAttributeName</code> <i>=</i>
     * <code>:hashval</code> <p>If you also want to provide a range key
     * condition, it must be combined using <i>AND</i> with the hash key
     * condition. Following is an example, using the <b>=</b> comparison
     * operator for the range key: <p> <code>hashAttributeName</code>
     * <i>=</i> <code>:hashval</code> <i>AND</i>
     * <code>rangeAttributeName</code> <i>=</i> <code>:rangeval</code>
     * <p>Valid comparisons for the range key condition are as follows: <ul>
     * <li> <p><code>rangeAttributeName</code> <i>=</i>
     * <code>:rangeval</code> - true if the range key is equal to
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><</i> <code>:rangeval</code> - true if the range key is less than
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><=</i> <code>:rangeval</code> - true if the range key is less than
     * or equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>></i> <code>:rangeval</code> -
     * true if the range key is greater than <code>:rangeval</code>. </li>
     * <li> <p><code>rangeAttributeName</code> <i>>=
     * </i><code>:rangeval</code> - true if the range key is greater than or
     * equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>BETWEEN</i>
     * <code>:rangeval1</code> <i>AND</i> <code>:rangeval2</code> - true if
     * the range key is greater than or equal to <code>:rangeval1</code>, and
     * less than or equal to <code>:rangeval2</code>. </li> <li>
     * <p><i>begins_with (</i><code>rangeAttributeName</code>,
     * <code>:rangeval</code><i>)</i> - true if the range key begins with a
     * particular operand. (You cannot use this function with a range key
     * that is of type Number.) Note that the function name
     * <code>begins_with</code> is case-sensitive. </li> </ul> <p>Use the
     * <i>ExpressionAttributeValues</i> parameter to replace tokens such as
     * <code>:hashval</code> and <code>:rangeval</code> with actual values at
     * runtime. <p>You can optionally use the <i>ExpressionAttributeNames</i>
     * parameter to replace the names of the hash and range attributes with
     * placeholder tokens. This option might be necessary if an attribute
     * name conflicts with a DynamoDB reserved word. For example, the
     * following <i>KeyConditionExpression</i> parameter causes an error
     * because <i>Size</i> is a reserved word: <ul> <li> <code>Size =
     * :myval</code> </li> </ul> <p>To work around this, define a placeholder
     * (such a <code>#S</code>) to represent the attribute name <i>Size</i>.
     * <i>KeyConditionExpression</i> then is as follows: <ul> <li> <code>#S =
     * :myval</code> </li> </ul> <p>For a list of reserved words, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>. <p>For more
     * information on <i>ExpressionAttributeNames</i> and
     * <i>ExpressionAttributeValues</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>. <note> <p><i>KeyConditionExpression</i>
     * replaces the legacy <i>KeyConditions</i> parameter. </note>
     *
     * @param keyConditionExpression The condition that specifies the key value(s) for items to be
     *         retrieved by the <i>Query</i> action. <p>The condition must perform an
     *         equality test on a single hash key value. The condition can also
     *         perform one of several comparison tests on a single range key value.
     *         <i>Query</i> can use <i>KeyConditionExpression</i> to retrieve one
     *         item with a given hash and range key value, or several items that have
     *         the same hash key value but different range key values. <p>The hash
     *         key equality test is required, and must be specified in the following
     *         format: <p> <code>hashAttributeName</code> <i>=</i>
     *         <code>:hashval</code> <p>If you also want to provide a range key
     *         condition, it must be combined using <i>AND</i> with the hash key
     *         condition. Following is an example, using the <b>=</b> comparison
     *         operator for the range key: <p> <code>hashAttributeName</code>
     *         <i>=</i> <code>:hashval</code> <i>AND</i>
     *         <code>rangeAttributeName</code> <i>=</i> <code>:rangeval</code>
     *         <p>Valid comparisons for the range key condition are as follows: <ul>
     *         <li> <p><code>rangeAttributeName</code> <i>=</i>
     *         <code>:rangeval</code> - true if the range key is equal to
     *         <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     *         <i><</i> <code>:rangeval</code> - true if the range key is less than
     *         <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     *         <i><=</i> <code>:rangeval</code> - true if the range key is less than
     *         or equal to <code>:rangeval</code>. </li> <li>
     *         <p><code>rangeAttributeName</code> <i>></i> <code>:rangeval</code> -
     *         true if the range key is greater than <code>:rangeval</code>. </li>
     *         <li> <p><code>rangeAttributeName</code> <i>>=
     *         </i><code>:rangeval</code> - true if the range key is greater than or
     *         equal to <code>:rangeval</code>. </li> <li>
     *         <p><code>rangeAttributeName</code> <i>BETWEEN</i>
     *         <code>:rangeval1</code> <i>AND</i> <code>:rangeval2</code> - true if
     *         the range key is greater than or equal to <code>:rangeval1</code>, and
     *         less than or equal to <code>:rangeval2</code>. </li> <li>
     *         <p><i>begins_with (</i><code>rangeAttributeName</code>,
     *         <code>:rangeval</code><i>)</i> - true if the range key begins with a
     *         particular operand. (You cannot use this function with a range key
     *         that is of type Number.) Note that the function name
     *         <code>begins_with</code> is case-sensitive. </li> </ul> <p>Use the
     *         <i>ExpressionAttributeValues</i> parameter to replace tokens such as
     *         <code>:hashval</code> and <code>:rangeval</code> with actual values at
     *         runtime. <p>You can optionally use the <i>ExpressionAttributeNames</i>
     *         parameter to replace the names of the hash and range attributes with
     *         placeholder tokens. This option might be necessary if an attribute
     *         name conflicts with a DynamoDB reserved word. For example, the
     *         following <i>KeyConditionExpression</i> parameter causes an error
     *         because <i>Size</i> is a reserved word: <ul> <li> <code>Size =
     *         :myval</code> </li> </ul> <p>To work around this, define a placeholder
     *         (such a <code>#S</code>) to represent the attribute name <i>Size</i>.
     *         <i>KeyConditionExpression</i> then is as follows: <ul> <li> <code>#S =
     *         :myval</code> </li> </ul> <p>For a list of reserved words, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     *         Words</a> in the <i>Amazon DynamoDB Developer Guide</i>. <p>For more
     *         information on <i>ExpressionAttributeNames</i> and
     *         <i>ExpressionAttributeValues</i>, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>. <note> <p><i>KeyConditionExpression</i>
     *         replaces the legacy <i>KeyConditions</i> parameter. </note>
     */
    public void setKeyConditionExpression(String keyConditionExpression) {
        this.keyConditionExpression = keyConditionExpression;
    }
    
    /**
     * The condition that specifies the key value(s) for items to be
     * retrieved by the <i>Query</i> action. <p>The condition must perform an
     * equality test on a single hash key value. The condition can also
     * perform one of several comparison tests on a single range key value.
     * <i>Query</i> can use <i>KeyConditionExpression</i> to retrieve one
     * item with a given hash and range key value, or several items that have
     * the same hash key value but different range key values. <p>The hash
     * key equality test is required, and must be specified in the following
     * format: <p> <code>hashAttributeName</code> <i>=</i>
     * <code>:hashval</code> <p>If you also want to provide a range key
     * condition, it must be combined using <i>AND</i> with the hash key
     * condition. Following is an example, using the <b>=</b> comparison
     * operator for the range key: <p> <code>hashAttributeName</code>
     * <i>=</i> <code>:hashval</code> <i>AND</i>
     * <code>rangeAttributeName</code> <i>=</i> <code>:rangeval</code>
     * <p>Valid comparisons for the range key condition are as follows: <ul>
     * <li> <p><code>rangeAttributeName</code> <i>=</i>
     * <code>:rangeval</code> - true if the range key is equal to
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><</i> <code>:rangeval</code> - true if the range key is less than
     * <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     * <i><=</i> <code>:rangeval</code> - true if the range key is less than
     * or equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>></i> <code>:rangeval</code> -
     * true if the range key is greater than <code>:rangeval</code>. </li>
     * <li> <p><code>rangeAttributeName</code> <i>>=
     * </i><code>:rangeval</code> - true if the range key is greater than or
     * equal to <code>:rangeval</code>. </li> <li>
     * <p><code>rangeAttributeName</code> <i>BETWEEN</i>
     * <code>:rangeval1</code> <i>AND</i> <code>:rangeval2</code> - true if
     * the range key is greater than or equal to <code>:rangeval1</code>, and
     * less than or equal to <code>:rangeval2</code>. </li> <li>
     * <p><i>begins_with (</i><code>rangeAttributeName</code>,
     * <code>:rangeval</code><i>)</i> - true if the range key begins with a
     * particular operand. (You cannot use this function with a range key
     * that is of type Number.) Note that the function name
     * <code>begins_with</code> is case-sensitive. </li> </ul> <p>Use the
     * <i>ExpressionAttributeValues</i> parameter to replace tokens such as
     * <code>:hashval</code> and <code>:rangeval</code> with actual values at
     * runtime. <p>You can optionally use the <i>ExpressionAttributeNames</i>
     * parameter to replace the names of the hash and range attributes with
     * placeholder tokens. This option might be necessary if an attribute
     * name conflicts with a DynamoDB reserved word. For example, the
     * following <i>KeyConditionExpression</i> parameter causes an error
     * because <i>Size</i> is a reserved word: <ul> <li> <code>Size =
     * :myval</code> </li> </ul> <p>To work around this, define a placeholder
     * (such a <code>#S</code>) to represent the attribute name <i>Size</i>.
     * <i>KeyConditionExpression</i> then is as follows: <ul> <li> <code>#S =
     * :myval</code> </li> </ul> <p>For a list of reserved words, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>. <p>For more
     * information on <i>ExpressionAttributeNames</i> and
     * <i>ExpressionAttributeValues</i>, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>. <note> <p><i>KeyConditionExpression</i>
     * replaces the legacy <i>KeyConditions</i> parameter. </note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param keyConditionExpression The condition that specifies the key value(s) for items to be
     *         retrieved by the <i>Query</i> action. <p>The condition must perform an
     *         equality test on a single hash key value. The condition can also
     *         perform one of several comparison tests on a single range key value.
     *         <i>Query</i> can use <i>KeyConditionExpression</i> to retrieve one
     *         item with a given hash and range key value, or several items that have
     *         the same hash key value but different range key values. <p>The hash
     *         key equality test is required, and must be specified in the following
     *         format: <p> <code>hashAttributeName</code> <i>=</i>
     *         <code>:hashval</code> <p>If you also want to provide a range key
     *         condition, it must be combined using <i>AND</i> with the hash key
     *         condition. Following is an example, using the <b>=</b> comparison
     *         operator for the range key: <p> <code>hashAttributeName</code>
     *         <i>=</i> <code>:hashval</code> <i>AND</i>
     *         <code>rangeAttributeName</code> <i>=</i> <code>:rangeval</code>
     *         <p>Valid comparisons for the range key condition are as follows: <ul>
     *         <li> <p><code>rangeAttributeName</code> <i>=</i>
     *         <code>:rangeval</code> - true if the range key is equal to
     *         <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     *         <i><</i> <code>:rangeval</code> - true if the range key is less than
     *         <code>:rangeval</code>. </li> <li> <p><code>rangeAttributeName</code>
     *         <i><=</i> <code>:rangeval</code> - true if the range key is less than
     *         or equal to <code>:rangeval</code>. </li> <li>
     *         <p><code>rangeAttributeName</code> <i>></i> <code>:rangeval</code> -
     *         true if the range key is greater than <code>:rangeval</code>. </li>
     *         <li> <p><code>rangeAttributeName</code> <i>>=
     *         </i><code>:rangeval</code> - true if the range key is greater than or
     *         equal to <code>:rangeval</code>. </li> <li>
     *         <p><code>rangeAttributeName</code> <i>BETWEEN</i>
     *         <code>:rangeval1</code> <i>AND</i> <code>:rangeval2</code> - true if
     *         the range key is greater than or equal to <code>:rangeval1</code>, and
     *         less than or equal to <code>:rangeval2</code>. </li> <li>
     *         <p><i>begins_with (</i><code>rangeAttributeName</code>,
     *         <code>:rangeval</code><i>)</i> - true if the range key begins with a
     *         particular operand. (You cannot use this function with a range key
     *         that is of type Number.) Note that the function name
     *         <code>begins_with</code> is case-sensitive. </li> </ul> <p>Use the
     *         <i>ExpressionAttributeValues</i> parameter to replace tokens such as
     *         <code>:hashval</code> and <code>:rangeval</code> with actual values at
     *         runtime. <p>You can optionally use the <i>ExpressionAttributeNames</i>
     *         parameter to replace the names of the hash and range attributes with
     *         placeholder tokens. This option might be necessary if an attribute
     *         name conflicts with a DynamoDB reserved word. For example, the
     *         following <i>KeyConditionExpression</i> parameter causes an error
     *         because <i>Size</i> is a reserved word: <ul> <li> <code>Size =
     *         :myval</code> </li> </ul> <p>To work around this, define a placeholder
     *         (such a <code>#S</code>) to represent the attribute name <i>Size</i>.
     *         <i>KeyConditionExpression</i> then is as follows: <ul> <li> <code>#S =
     *         :myval</code> </li> </ul> <p>For a list of reserved words, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     *         Words</a> in the <i>Amazon DynamoDB Developer Guide</i>. <p>For more
     *         information on <i>ExpressionAttributeNames</i> and
     *         <i>ExpressionAttributeValues</i>, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>. <note> <p><i>KeyConditionExpression</i>
     *         replaces the legacy <i>KeyConditions</i> parameter. </note>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withKeyConditionExpression(String keyConditionExpression) {
        this.keyConditionExpression = keyConditionExpression;
        return this;
    }

    /**
     * One or more substitution tokens for attribute names in an expression.
     * The following are some use cases for using
     * <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     * whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     * create a placeholder for repeating occurrences of an attribute name in
     * an expression. </li> <li> <p>To prevent special characters in an
     * attribute name from being misinterpreted in an expression. </li> </ul>
     * <p>Use the <b>#</b> character in an expression to dereference an
     * attribute name. For example, consider the following attribute name:
     * <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     * attribute conflicts with a reserved word, so it cannot be used
     * directly in an expression. (For the complete list of reserved words,
     * see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     * around this, you could specify the following for
     * <i>ExpressionAttributeNames</i>:
     * <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     * then use this substitution in an expression, as in this example:
     * <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     * with the <b>:</b> character are <i>expression attribute values</i>,
     * which are placeholders for the actual value at runtime.</note> <p>For
     * more information on expression attribute names, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     *
     * @return One or more substitution tokens for attribute names in an expression.
     *         The following are some use cases for using
     *         <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     *         whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     *         create a placeholder for repeating occurrences of an attribute name in
     *         an expression. </li> <li> <p>To prevent special characters in an
     *         attribute name from being misinterpreted in an expression. </li> </ul>
     *         <p>Use the <b>#</b> character in an expression to dereference an
     *         attribute name. For example, consider the following attribute name:
     *         <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     *         attribute conflicts with a reserved word, so it cannot be used
     *         directly in an expression. (For the complete list of reserved words,
     *         see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     *         Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     *         around this, you could specify the following for
     *         <i>ExpressionAttributeNames</i>:
     *         <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     *         then use this substitution in an expression, as in this example:
     *         <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     *         with the <b>:</b> character are <i>expression attribute values</i>,
     *         which are placeholders for the actual value at runtime.</note> <p>For
     *         more information on expression attribute names, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>.
     */
    public java.util.Map<String,String> getExpressionAttributeNames() {
        
        return expressionAttributeNames;
    }
    
    /**
     * One or more substitution tokens for attribute names in an expression.
     * The following are some use cases for using
     * <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     * whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     * create a placeholder for repeating occurrences of an attribute name in
     * an expression. </li> <li> <p>To prevent special characters in an
     * attribute name from being misinterpreted in an expression. </li> </ul>
     * <p>Use the <b>#</b> character in an expression to dereference an
     * attribute name. For example, consider the following attribute name:
     * <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     * attribute conflicts with a reserved word, so it cannot be used
     * directly in an expression. (For the complete list of reserved words,
     * see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     * around this, you could specify the following for
     * <i>ExpressionAttributeNames</i>:
     * <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     * then use this substitution in an expression, as in this example:
     * <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     * with the <b>:</b> character are <i>expression attribute values</i>,
     * which are placeholders for the actual value at runtime.</note> <p>For
     * more information on expression attribute names, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     *
     * @param expressionAttributeNames One or more substitution tokens for attribute names in an expression.
     *         The following are some use cases for using
     *         <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     *         whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     *         create a placeholder for repeating occurrences of an attribute name in
     *         an expression. </li> <li> <p>To prevent special characters in an
     *         attribute name from being misinterpreted in an expression. </li> </ul>
     *         <p>Use the <b>#</b> character in an expression to dereference an
     *         attribute name. For example, consider the following attribute name:
     *         <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     *         attribute conflicts with a reserved word, so it cannot be used
     *         directly in an expression. (For the complete list of reserved words,
     *         see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     *         Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     *         around this, you could specify the following for
     *         <i>ExpressionAttributeNames</i>:
     *         <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     *         then use this substitution in an expression, as in this example:
     *         <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     *         with the <b>:</b> character are <i>expression attribute values</i>,
     *         which are placeholders for the actual value at runtime.</note> <p>For
     *         more information on expression attribute names, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>.
     */
    public void setExpressionAttributeNames(java.util.Map<String,String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
    }
    
    /**
     * One or more substitution tokens for attribute names in an expression.
     * The following are some use cases for using
     * <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     * whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     * create a placeholder for repeating occurrences of an attribute name in
     * an expression. </li> <li> <p>To prevent special characters in an
     * attribute name from being misinterpreted in an expression. </li> </ul>
     * <p>Use the <b>#</b> character in an expression to dereference an
     * attribute name. For example, consider the following attribute name:
     * <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     * attribute conflicts with a reserved word, so it cannot be used
     * directly in an expression. (For the complete list of reserved words,
     * see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     * around this, you could specify the following for
     * <i>ExpressionAttributeNames</i>:
     * <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     * then use this substitution in an expression, as in this example:
     * <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     * with the <b>:</b> character are <i>expression attribute values</i>,
     * which are placeholders for the actual value at runtime.</note> <p>For
     * more information on expression attribute names, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param expressionAttributeNames One or more substitution tokens for attribute names in an expression.
     *         The following are some use cases for using
     *         <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     *         whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     *         create a placeholder for repeating occurrences of an attribute name in
     *         an expression. </li> <li> <p>To prevent special characters in an
     *         attribute name from being misinterpreted in an expression. </li> </ul>
     *         <p>Use the <b>#</b> character in an expression to dereference an
     *         attribute name. For example, consider the following attribute name:
     *         <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     *         attribute conflicts with a reserved word, so it cannot be used
     *         directly in an expression. (For the complete list of reserved words,
     *         see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     *         Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     *         around this, you could specify the following for
     *         <i>ExpressionAttributeNames</i>:
     *         <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     *         then use this substitution in an expression, as in this example:
     *         <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     *         with the <b>:</b> character are <i>expression attribute values</i>,
     *         which are placeholders for the actual value at runtime.</note> <p>For
     *         more information on expression attribute names, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withExpressionAttributeNames(java.util.Map<String,String> expressionAttributeNames) {
        setExpressionAttributeNames(expressionAttributeNames);
        return this;
    }

    /**
     * One or more substitution tokens for attribute names in an expression.
     * The following are some use cases for using
     * <i>ExpressionAttributeNames</i>: <ul> <li> <p>To access an attribute
     * whose name conflicts with a DynamoDB reserved word. </li> <li> <p>To
     * create a placeholder for repeating occurrences of an attribute name in
     * an expression. </li> <li> <p>To prevent special characters in an
     * attribute name from being misinterpreted in an expression. </li> </ul>
     * <p>Use the <b>#</b> character in an expression to dereference an
     * attribute name. For example, consider the following attribute name:
     * <ul><li><p><code>Percentile</code></li></ul> <p>The name of this
     * attribute conflicts with a reserved word, so it cannot be used
     * directly in an expression. (For the complete list of reserved words,
     * see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ReservedWords.html">Reserved
     * Words</a> in the <i>Amazon DynamoDB Developer Guide</i>). To work
     * around this, you could specify the following for
     * <i>ExpressionAttributeNames</i>:
     * <ul><li><p><code>{"#P":"Percentile"}</code></li></ul> <p>You could
     * then use this substitution in an expression, as in this example:
     * <ul><li><p><code>#P = :val</code></li></ul> <note><p>Tokens that begin
     * with the <b>:</b> character are <i>expression attribute values</i>,
     * which are placeholders for the actual value at runtime.</note> <p>For
     * more information on expression attribute names, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     * <p>
     * The method adds a new key-value pair into ExpressionAttributeNames
     * parameter, and returns a reference to this object so that method calls
     * can be chained together.
     *
     * @param key The key of the entry to be added into ExpressionAttributeNames.
     * @param value The corresponding value of the entry to be added into ExpressionAttributeNames.
     */
  public QueryRequest addExpressionAttributeNamesEntry(String key, String value) {
    if (null == this.expressionAttributeNames) {
      this.expressionAttributeNames = new java.util.HashMap<String,String>();
    }
    if (this.expressionAttributeNames.containsKey(key))
      throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
    this.expressionAttributeNames.put(key, value);
    return this;
  }

  /**
   * Removes all the entries added into ExpressionAttributeNames.
   * <p>
   * Returns a reference to this object so that method calls can be chained together.
   */
  public QueryRequest clearExpressionAttributeNamesEntries() {
    this.expressionAttributeNames = null;
    return this;
  }
  
    /**
     * One or more values that can be substituted in an expression. <p>Use
     * the <b>:</b> (colon) character in an expression to dereference an
     * attribute value. For example, suppose that you wanted to check whether
     * the value of the <i>ProductStatus</i> attribute was one of the
     * following: <p><code>Available | Backordered | Discontinued</code>
     * <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     * follows: <p><code>{ ":avail":{"S":"Available"},
     * ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     * <p>You could then use these values in an expression, such as this:
     * <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     * information on expression attribute values, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     *
     * @return One or more values that can be substituted in an expression. <p>Use
     *         the <b>:</b> (colon) character in an expression to dereference an
     *         attribute value. For example, suppose that you wanted to check whether
     *         the value of the <i>ProductStatus</i> attribute was one of the
     *         following: <p><code>Available | Backordered | Discontinued</code>
     *         <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     *         follows: <p><code>{ ":avail":{"S":"Available"},
     *         ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     *         <p>You could then use these values in an expression, such as this:
     *         <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     *         information on expression attribute values, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>.
     */
    public java.util.Map<String,AttributeValue> getExpressionAttributeValues() {
        
        return expressionAttributeValues;
    }
    
    /**
     * One or more values that can be substituted in an expression. <p>Use
     * the <b>:</b> (colon) character in an expression to dereference an
     * attribute value. For example, suppose that you wanted to check whether
     * the value of the <i>ProductStatus</i> attribute was one of the
     * following: <p><code>Available | Backordered | Discontinued</code>
     * <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     * follows: <p><code>{ ":avail":{"S":"Available"},
     * ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     * <p>You could then use these values in an expression, such as this:
     * <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     * information on expression attribute values, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     *
     * @param expressionAttributeValues One or more values that can be substituted in an expression. <p>Use
     *         the <b>:</b> (colon) character in an expression to dereference an
     *         attribute value. For example, suppose that you wanted to check whether
     *         the value of the <i>ProductStatus</i> attribute was one of the
     *         following: <p><code>Available | Backordered | Discontinued</code>
     *         <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     *         follows: <p><code>{ ":avail":{"S":"Available"},
     *         ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     *         <p>You could then use these values in an expression, such as this:
     *         <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     *         information on expression attribute values, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>.
     */
    public void setExpressionAttributeValues(java.util.Map<String,AttributeValue> expressionAttributeValues) {
        this.expressionAttributeValues = expressionAttributeValues;
    }
    
    /**
     * One or more values that can be substituted in an expression. <p>Use
     * the <b>:</b> (colon) character in an expression to dereference an
     * attribute value. For example, suppose that you wanted to check whether
     * the value of the <i>ProductStatus</i> attribute was one of the
     * following: <p><code>Available | Backordered | Discontinued</code>
     * <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     * follows: <p><code>{ ":avail":{"S":"Available"},
     * ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     * <p>You could then use these values in an expression, such as this:
     * <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     * information on expression attribute values, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param expressionAttributeValues One or more values that can be substituted in an expression. <p>Use
     *         the <b>:</b> (colon) character in an expression to dereference an
     *         attribute value. For example, suppose that you wanted to check whether
     *         the value of the <i>ProductStatus</i> attribute was one of the
     *         following: <p><code>Available | Backordered | Discontinued</code>
     *         <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     *         follows: <p><code>{ ":avail":{"S":"Available"},
     *         ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     *         <p>You could then use these values in an expression, such as this:
     *         <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     *         information on expression attribute values, see <a
     *         href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     *         Placeholders for Attribute Names and Values</a> in the <i>Amazon
     *         DynamoDB Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public QueryRequest withExpressionAttributeValues(java.util.Map<String,AttributeValue> expressionAttributeValues) {
        setExpressionAttributeValues(expressionAttributeValues);
        return this;
    }

    /**
     * One or more values that can be substituted in an expression. <p>Use
     * the <b>:</b> (colon) character in an expression to dereference an
     * attribute value. For example, suppose that you wanted to check whether
     * the value of the <i>ProductStatus</i> attribute was one of the
     * following: <p><code>Available | Backordered | Discontinued</code>
     * <p>You would first need to specify <i>ExpressionAttributeValues</i> as
     * follows: <p><code>{ ":avail":{"S":"Available"},
     * ":back":{"S":"Backordered"}, ":disc":{"S":"Discontinued"} }</code>
     * <p>You could then use these values in an expression, such as this:
     * <p><code>ProductStatus IN (:avail, :back, :disc)</code> <p>For more
     * information on expression attribute values, see <a
     * href="http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.ExpressionPlaceholders.html">Using
     * Placeholders for Attribute Names and Values</a> in the <i>Amazon
     * DynamoDB Developer Guide</i>.
     * <p>
     * The method adds a new key-value pair into ExpressionAttributeValues
     * parameter, and returns a reference to this object so that method calls
     * can be chained together.
     *
     * @param key The key of the entry to be added into ExpressionAttributeValues.
     * @param value The corresponding value of the entry to be added into ExpressionAttributeValues.
     */
  public QueryRequest addExpressionAttributeValuesEntry(String key, AttributeValue value) {
    if (null == this.expressionAttributeValues) {
      this.expressionAttributeValues = new java.util.HashMap<String,AttributeValue>();
    }
    if (this.expressionAttributeValues.containsKey(key))
      throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
    this.expressionAttributeValues.put(key, value);
    return this;
  }

  /**
   * Removes all the entries added into ExpressionAttributeValues.
   * <p>
   * Returns a reference to this object so that method calls can be chained together.
   */
  public QueryRequest clearExpressionAttributeValuesEntries() {
    this.expressionAttributeValues = null;
    return this;
  }
  
    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableName() != null) sb.append("TableName: " + getTableName() + ",");
        if (getIndexName() != null) sb.append("IndexName: " + getIndexName() + ",");
        if (getSelect() != null) sb.append("Select: " + getSelect() + ",");
        if (getAttributesToGet() != null) sb.append("AttributesToGet: " + getAttributesToGet() + ",");
        if (getLimit() != null) sb.append("Limit: " + getLimit() + ",");
        if (isConsistentRead() != null) sb.append("ConsistentRead: " + isConsistentRead() + ",");
        if (getKeyConditions() != null) sb.append("KeyConditions: " + getKeyConditions() + ",");
        if (getQueryFilter() != null) sb.append("QueryFilter: " + getQueryFilter() + ",");
        if (getConditionalOperator() != null) sb.append("ConditionalOperator: " + getConditionalOperator() + ",");
        if (isScanIndexForward() != null) sb.append("ScanIndexForward: " + isScanIndexForward() + ",");
        if (getExclusiveStartKey() != null) sb.append("ExclusiveStartKey: " + getExclusiveStartKey() + ",");
        if (getReturnConsumedCapacity() != null) sb.append("ReturnConsumedCapacity: " + getReturnConsumedCapacity() + ",");
        if (getProjectionExpression() != null) sb.append("ProjectionExpression: " + getProjectionExpression() + ",");
        if (getFilterExpression() != null) sb.append("FilterExpression: " + getFilterExpression() + ",");
        if (getKeyConditionExpression() != null) sb.append("KeyConditionExpression: " + getKeyConditionExpression() + ",");
        if (getExpressionAttributeNames() != null) sb.append("ExpressionAttributeNames: " + getExpressionAttributeNames() + ",");
        if (getExpressionAttributeValues() != null) sb.append("ExpressionAttributeValues: " + getExpressionAttributeValues() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getTableName() == null) ? 0 : getTableName().hashCode()); 
        hashCode = prime * hashCode + ((getIndexName() == null) ? 0 : getIndexName().hashCode()); 
        hashCode = prime * hashCode + ((getSelect() == null) ? 0 : getSelect().hashCode()); 
        hashCode = prime * hashCode + ((getAttributesToGet() == null) ? 0 : getAttributesToGet().hashCode()); 
        hashCode = prime * hashCode + ((getLimit() == null) ? 0 : getLimit().hashCode()); 
        hashCode = prime * hashCode + ((isConsistentRead() == null) ? 0 : isConsistentRead().hashCode()); 
        hashCode = prime * hashCode + ((getKeyConditions() == null) ? 0 : getKeyConditions().hashCode()); 
        hashCode = prime * hashCode + ((getQueryFilter() == null) ? 0 : getQueryFilter().hashCode()); 
        hashCode = prime * hashCode + ((getConditionalOperator() == null) ? 0 : getConditionalOperator().hashCode()); 
        hashCode = prime * hashCode + ((isScanIndexForward() == null) ? 0 : isScanIndexForward().hashCode()); 
        hashCode = prime * hashCode + ((getExclusiveStartKey() == null) ? 0 : getExclusiveStartKey().hashCode()); 
        hashCode = prime * hashCode + ((getReturnConsumedCapacity() == null) ? 0 : getReturnConsumedCapacity().hashCode()); 
        hashCode = prime * hashCode + ((getProjectionExpression() == null) ? 0 : getProjectionExpression().hashCode()); 
        hashCode = prime * hashCode + ((getFilterExpression() == null) ? 0 : getFilterExpression().hashCode()); 
        hashCode = prime * hashCode + ((getKeyConditionExpression() == null) ? 0 : getKeyConditionExpression().hashCode()); 
        hashCode = prime * hashCode + ((getExpressionAttributeNames() == null) ? 0 : getExpressionAttributeNames().hashCode()); 
        hashCode = prime * hashCode + ((getExpressionAttributeValues() == null) ? 0 : getExpressionAttributeValues().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof QueryRequest == false) return false;
        QueryRequest other = (QueryRequest)obj;
        
        if (other.getTableName() == null ^ this.getTableName() == null) return false;
        if (other.getTableName() != null && other.getTableName().equals(this.getTableName()) == false) return false; 
        if (other.getIndexName() == null ^ this.getIndexName() == null) return false;
        if (other.getIndexName() != null && other.getIndexName().equals(this.getIndexName()) == false) return false; 
        if (other.getSelect() == null ^ this.getSelect() == null) return false;
        if (other.getSelect() != null && other.getSelect().equals(this.getSelect()) == false) return false; 
        if (other.getAttributesToGet() == null ^ this.getAttributesToGet() == null) return false;
        if (other.getAttributesToGet() != null && other.getAttributesToGet().equals(this.getAttributesToGet()) == false) return false; 
        if (other.getLimit() == null ^ this.getLimit() == null) return false;
        if (other.getLimit() != null && other.getLimit().equals(this.getLimit()) == false) return false; 
        if (other.isConsistentRead() == null ^ this.isConsistentRead() == null) return false;
        if (other.isConsistentRead() != null && other.isConsistentRead().equals(this.isConsistentRead()) == false) return false; 
        if (other.getKeyConditions() == null ^ this.getKeyConditions() == null) return false;
        if (other.getKeyConditions() != null && other.getKeyConditions().equals(this.getKeyConditions()) == false) return false; 
        if (other.getQueryFilter() == null ^ this.getQueryFilter() == null) return false;
        if (other.getQueryFilter() != null && other.getQueryFilter().equals(this.getQueryFilter()) == false) return false; 
        if (other.getConditionalOperator() == null ^ this.getConditionalOperator() == null) return false;
        if (other.getConditionalOperator() != null && other.getConditionalOperator().equals(this.getConditionalOperator()) == false) return false; 
        if (other.isScanIndexForward() == null ^ this.isScanIndexForward() == null) return false;
        if (other.isScanIndexForward() != null && other.isScanIndexForward().equals(this.isScanIndexForward()) == false) return false; 
        if (other.getExclusiveStartKey() == null ^ this.getExclusiveStartKey() == null) return false;
        if (other.getExclusiveStartKey() != null && other.getExclusiveStartKey().equals(this.getExclusiveStartKey()) == false) return false; 
        if (other.getReturnConsumedCapacity() == null ^ this.getReturnConsumedCapacity() == null) return false;
        if (other.getReturnConsumedCapacity() != null && other.getReturnConsumedCapacity().equals(this.getReturnConsumedCapacity()) == false) return false; 
        if (other.getProjectionExpression() == null ^ this.getProjectionExpression() == null) return false;
        if (other.getProjectionExpression() != null && other.getProjectionExpression().equals(this.getProjectionExpression()) == false) return false; 
        if (other.getFilterExpression() == null ^ this.getFilterExpression() == null) return false;
        if (other.getFilterExpression() != null && other.getFilterExpression().equals(this.getFilterExpression()) == false) return false; 
        if (other.getKeyConditionExpression() == null ^ this.getKeyConditionExpression() == null) return false;
        if (other.getKeyConditionExpression() != null && other.getKeyConditionExpression().equals(this.getKeyConditionExpression()) == false) return false; 
        if (other.getExpressionAttributeNames() == null ^ this.getExpressionAttributeNames() == null) return false;
        if (other.getExpressionAttributeNames() != null && other.getExpressionAttributeNames().equals(this.getExpressionAttributeNames()) == false) return false; 
        if (other.getExpressionAttributeValues() == null ^ this.getExpressionAttributeValues() == null) return false;
        if (other.getExpressionAttributeValues() != null && other.getExpressionAttributeValues().equals(this.getExpressionAttributeValues()) == false) return false; 
        return true;
    }
    
    @Override
    public QueryRequest clone() {
        
            return (QueryRequest) super.clone();
    }

}
    