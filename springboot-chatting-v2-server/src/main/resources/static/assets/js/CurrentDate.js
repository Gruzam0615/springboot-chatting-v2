const $CURRENT_DATE = new Date();
    const YEAR = $CURRENT_DATE.getFullYear();
    const MONTH = $CURRENT_DATE.getMonth()+1;
    const DATE = $CURRENT_DATE.getDate();
    const HH = $CURRENT_DATE.getHours();
    const MM = $CURRENT_DATE.getMinutes();
    const ss = $CURRENT_DATE.getSeconds();
    const ms = $CURRENT_DATE.getMilliseconds();
    const $CURRENT_TIME = `${YEAR}-${("00"+MONTH.toString()).slice(-2)}-${("00"+DATE.toString()).slice(-2)}T${HH}:${MM}:${ss}.${ms}`;

class CurrentDate {
    constructor(year, month, date, hh, mm, ss, ms) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
        this.ms = ms;
    }

    ShowCurrentDate() {
        const cd = new Date();
        this.year = cd.getFullYear();
        this.month = cd.getMonth() + 1;
        this.date = cd.getDate();
        this.hh = cd.getHours();
        this.mm = cd.getMinutes();
        this.ss = cd.getSeconds();
        this.ms = cd.getMilliseconds();
        this.currentTime = 
        `${this.year}-${("00"+this.month.toString()).slice(-2)}-${("00"+this.date.toString()).slice(-2)}T`+
        `${this.hh}:${this.mm}:${this.ss}.${this.ms}`;
        return this.currentTime;
    }
}
