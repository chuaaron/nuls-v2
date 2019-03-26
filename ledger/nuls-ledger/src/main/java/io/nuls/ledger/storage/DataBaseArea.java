/*-
 * ⁣⁣
 * MIT License
 * ⁣⁣
 * Copyright (C) 2017 - 2018 nuls.io
 * ⁣⁣
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * ⁣⁣
 */
package io.nuls.ledger.storage;

/**
 * database table name constant
 * Created by wangkun23 on 2018/11/19.
 */
public interface DataBaseArea {
    String TB_LEDGER_ACCOUNT = "account";

    /**
     *   按区块高度来进行上一个账号状态的
     */

    String TB_LEDGER_ACCOUNT_BLOCK_SNAPSHOT = "accountBlockSnapshot";

    /**
     *   存区块当前确认的高度
     */
    String TB_LEDGER_BLOCK_HEIGHT = "chainBlockHeight";

    /**
     *   存区块交易缓存数据
     */
    String TB_LEDGER_BLOCKS = "chainBlocks";

    /**
     *   存区块交易缓存数据
     */
    String TB_LEDGER_NONCES = "ledgerNonces";
}