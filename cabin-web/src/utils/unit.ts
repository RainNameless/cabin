/**
 * 计算文件大小
 * @return {number}                   文件大小（字节）
 */
export function netSpeedConvert(size: string) {
  const sizeNum = Number(size);
  if (sizeNum < 1024) {
    return `${size}B/s`;
  }
  if (sizeNum >= 1024 && sizeNum < pow(1024, 2)) {
    return `${parseFloat(String(sizeNum / 1024)).toFixed(2)}KB/s`;
  }
  if (sizeNum >= pow(1024, 2) && sizeNum < pow(1024, 3)) {
    return `${parseFloat(String(sizeNum / pow(1024, 2))).toFixed(2)}MB/s`;
  }
  if (sizeNum > pow(1024, 3)) {
    return `${parseFloat(String(sizeNum / pow(1024, 3))).toFixed(2)}GB/s`;
  }
  return `${0}B/s`;
}

/**
 * 计算内存大小
 * @return {number}                   文件大小（字节）
 */
export function memConvert(size: string) {
  const sizeNum = Number(size);
  if (sizeNum < 1024) {
    return `${size}B`;
  }
  if (sizeNum >= 1024 && sizeNum < pow(1024, 2)) {
    return `${parseFloat(String(sizeNum / 1024)).toFixed(0)}KB`;
  }
  if (sizeNum >= pow(1024, 2) && sizeNum < pow(1024, 3)) {
    return `${parseFloat(String(sizeNum / pow(1024, 2))).toFixed(0)}MB`;
  }
  if (sizeNum > pow(1024, 3)) {
    return `${parseFloat(String(sizeNum / pow(1024, 3))).toFixed(0)}GB`;
  }
  return `${0}B`;
}

/**
 * 计算次方
 * @param x
 * @param n
 */
// eslint-disable-next-line func-names
function pow(x: number, n: number) {
  let r = 1;
  const len = Math.abs(n);
  // eslint-disable-next-line no-plusplus
  for (let i = len; i > 0; i--) {
    if (i % 2 === 0) {
      // eslint-disable-next-line no-param-reassign
      x *= x;
      i /= 2;
    }
    r *= x;
  }
  if (n < 0) {
    r = 1 / r;
  }
  return r;
}
